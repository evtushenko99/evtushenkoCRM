package com.evtushenko.crm.di.clients

import com.evtushenko.crm.domain.ClientsRepository
import com.evtushenko.crm.presentation.clients.ClientsViewModel
import dagger.Module
import dagger.Provides

@Module
object ClientsModule {

    @Provides
    fun providesClientsViewModel(repository: ClientsRepository): ClientsViewModel.Factory =
        ClientsViewModel.Factory(repository)
}