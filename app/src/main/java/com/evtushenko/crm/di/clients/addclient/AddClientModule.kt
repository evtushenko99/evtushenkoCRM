package com.evtushenko.crm.di.clients.addclient

import com.evtushenko.crm.domain.AddClientsRepository
import com.evtushenko.crm.presentation.clients.addclient.AddClientsViewModel
import dagger.Module
import dagger.Provides

@Module
object AddClientModule {

    @Provides
    fun providesAddClientsViewModel(repository: AddClientsRepository): AddClientsViewModel.Factory =
        AddClientsViewModel.Factory(repository)
}