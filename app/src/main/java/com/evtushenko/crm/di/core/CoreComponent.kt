package com.evtushenko.crm.di.core

import com.evtushenko.crm.data.ClientsRepositoryImpl
import com.evtushenko.crm.di.AppComponent
import com.evtushenko.crm.domain.AddClientsRepository
import com.evtushenko.crm.domain.ClientsRepository
import dagger.Component

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 16.02.2024
 */
@Component(
    dependencies = [AppComponent::class],
    modules = [NetworkModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CoreComponent
    }

    /**
     * Провайдит [AddClientsRepository] компонентам на уровень ниже
     */
    fun providesAddClientsRepository(): AddClientsRepository

    /**
     * Провайдит [ClientsRepository] компонентам на уровень ниже
     */
    fun providesClientsRepository(): ClientsRepository
}