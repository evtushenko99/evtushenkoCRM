package com.evtushenko.crm.di.clients.addclient

import com.evtushenko.crm.di.core.CoreComponent
import com.evtushenko.crm.presentation.clients.addclient.AddClientFragment
import dagger.Component

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 10.02.2024
 */
@Component(
    modules = [AddClientModule::class],
    dependencies = [CoreComponent::class]
)
interface AddClientComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AddClientComponent
    }

    fun inject(addClientFragment: AddClientFragment)
}