package com.evtushenko.crm

import android.app.Application
import com.evtushenko.crm.di.AppComponent
import com.evtushenko.crm.di.DaggerAppComponent

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 16.02.2024
 */
class App : Application() {
    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this.applicationContext)
    }
}