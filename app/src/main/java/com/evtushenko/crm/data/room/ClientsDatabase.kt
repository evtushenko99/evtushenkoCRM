package com.evtushenko.crm.data.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.evtushenko.crm.models.data.ClientDataRoomEntity

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
@Database(
    entities = [ClientDataRoomEntity::class], version = 1
)
abstract class ClientsDatabase : RoomDatabase() {

    abstract fun getClientsDao(): ClientsDao
}