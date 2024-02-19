package com.evtushenko.crm.di.clients

import com.evtushenko.crm.di.core.CoreComponent
import com.evtushenko.crm.presentation.clients.ClientsFragment
import dagger.Component

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
@Component(
    modules = [ClientsModule::class],
    dependencies = [CoreComponent::class]
)
interface ClientsComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): ClientsComponent
    }

    fun inject(fragment: ClientsFragment)
}