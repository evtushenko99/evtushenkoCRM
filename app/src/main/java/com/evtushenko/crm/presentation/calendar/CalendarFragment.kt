package com.evtushenko.crm.presentation.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.evtushenko.crm.databinding.FragmentCalendarBinding
import com.evtushenko.crm.databinding.FragmentClientsBinding
import com.evtushenko.crm.presentation.clients.ClientsAdapter
import com.evtushenko.crm.presentation.clients.ClientsViewModel
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.view.CalendarView
import com.kizitonwose.calendar.view.MonthDayBinder
import javax.inject.Inject

class CalendarFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CalendarViewModel.Factory


    private val viewModel: ClientsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ClientsViewModel::class.java]
    }
    private lateinit var adapter: ClientsAdapter
    private lateinit var navController: NavController
    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val calendarViewModel =
            ViewModelProvider(this)[CalendarViewModel::class.java]

        binding = FragmentCalendarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // _binding = null
    }
}