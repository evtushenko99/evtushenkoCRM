package com.evtushenko.crm.presentation.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evtushenko.crm.R
import com.evtushenko.crm.databinding.FragmentClientsBinding
import com.evtushenko.crm.di.clients.DaggerClientsComponent
import com.evtushenko.crm.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClientsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ClientsViewModel.Factory


    private val viewModel: ClientsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ClientsViewModel::class.java]
    }
    private lateinit var adapter: ClientsAdapter
    private lateinit var navController: NavController
    private lateinit var binding: FragmentClientsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        DaggerClientsComponent.factory()
            .create((this.activity as MainActivity).coreComponent)
            .inject(this)



        adapter = ClientsAdapter()

        binding = FragmentClientsBinding.inflate(inflater, container, false)
        binding.clientsRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.clientsRecycler.adapter = adapter
        navController = findNavController()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clientsAddClientButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_clients_to_add_client)
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiResult.collect {
                    when (it) {
                        ClientUiState.Idle -> Unit
                        is ClientUiState.Error -> {
                            binding.clientsProgressBar.visibility = View.GONE
                            binding.clientsRecycler.visibility = View.GONE
                            Snackbar
                                .make(binding.root, it.message, Snackbar.LENGTH_LONG)
                                .show()
                            TODO("Сделать все через рекуклер")
                        }

                        is ClientUiState.Loading -> {
                            binding.clientsProgressBar.visibility = View.VISIBLE
                            binding.clientsRecycler.visibility = View.GONE
                        }

                        is ClientUiState.Success -> {
                            binding.clientsProgressBar.visibility = View.GONE
                            binding.clientsRecycler.visibility = View.VISIBLE
                            adapter.submitList(it.clients)
                        }
                    }
                }
            }
        }

        viewModel.getClients()
    }
}