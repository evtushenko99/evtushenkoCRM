package com.evtushenko.crm.di.core

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.evtushenko.crm.data.ClientsLocalDataStore
import com.evtushenko.crm.data.ClientsRemoteDataStore
import com.evtushenko.crm.data.ClientsRepositoryImpl
import com.evtushenko.crm.data.room.ClientsDao
import com.evtushenko.crm.data.room.ClientsDatabase
import com.evtushenko.crm.di.AppContext
import com.evtushenko.crm.domain.AddClientsRepository
import com.evtushenko.crm.domain.ClientsRepository
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {

    @Provides
    fun providesAddClientsRepository(repository: ClientsRepositoryImpl): AddClientsRepository =
        repository

    @Provides
    fun providesClientsRepository(repository: ClientsRepositoryImpl): ClientsRepository =
        repository

    @Provides
    fun provideClientsRepositoryImpl(
        clientsDao: ClientsDao
    ): ClientsRepositoryImpl {
        return ClientsRepositoryImpl(
            localStore = ClientsLocalDataStore(clientsDao),
            remoteDataStore = ClientsRemoteDataStore()
        )
    }

    @Provides
    fun provideClientsDao(@AppContext context: Context): ClientsDao =
        Room.databaseBuilder(context, ClientsDatabase::class.java, "clients.db")
            .build().getClientsDao()

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE clients ADD COLUMN gender TEXT")
        }
    }
}