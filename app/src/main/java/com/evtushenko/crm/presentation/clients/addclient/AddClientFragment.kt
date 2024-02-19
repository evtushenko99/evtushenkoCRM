package com.evtushenko.crm.presentation.clients.addclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.evtushenko.crm.R
import com.evtushenko.crm.databinding.FragmentAddClientBinding
import com.evtushenko.crm.di.clients.addclient.DaggerAddClientComponent
import com.evtushenko.crm.models.ClientAddEntity
import com.evtushenko.crm.presentation.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddClientFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: AddClientsViewModel.Factory

    private val viewModel: AddClientsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AddClientsViewModel::class.java]
    }
    private lateinit var binding: FragmentAddClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAddClientComponent.factory()
            .create((this.activity as MainActivity).coreComponent)
            .inject(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiResult.collect {
                    when (it) {
                        AddClientUiState.Close -> findNavController().navigate(R.id.action_navigation_add_client_pop)
                        AddClientUiState.Idle -> Unit

                        is AddClientUiState.Loading -> {
                            binding.addClientProgressBar.visibility = if (it.show) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                        }

                        is AddClientUiState.ShowSnackBar -> {
                            Snackbar.make(
                                /* view = */ binding.root,
                                /* text = */ it.message,
                                /* duration = */ Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAddClientBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addClientReadyButton.setOnClickListener {
            viewModel.addClient(
                ClientAddEntity(
                    name = binding.addClientNameEditText.text.toString(),
                    lastName = binding.addClientSurnameEditText.text.toString(),
                    phone = binding.addClientPhoneEditText.text.toString(),
                    birthday = binding.addClientBirthdayEditText.text.toString(),
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //   activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}