package com.evtushenko.crm.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.evtushenko.crm.App
import com.evtushenko.crm.R
import com.evtushenko.crm.databinding.ActivityMainBinding
import com.evtushenko.crm.di.core.CoreComponent
import com.evtushenko.crm.di.core.DaggerCoreComponent
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var coreComponent: CoreComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coreComponent = DaggerCoreComponent.factory().create((this.application as App).component)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_clients,
                R.id.navigation_calendar
            )
        )
        setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        ) // TODO("ПОнять, как работает")
        navView.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}