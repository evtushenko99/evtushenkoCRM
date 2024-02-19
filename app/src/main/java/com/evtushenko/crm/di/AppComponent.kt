package com.evtushenko.crm.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Qualifier
import javax.inject.Singleton

@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @AppContext context: Context): AppComponent
    }

    @AppContext
    fun providesAppContext(): Context
}

@Qualifier
annotation class AppContext
