package com.evtushenko.crm.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.evtushenko.crm.models.data.ClientDataRoomEntity

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
@Dao
interface ClientsDao {
    @Query("SELECT * FROM clients")
    suspend fun getAll(): List<ClientDataRoomEntity>

    @Insert
    suspend fun insertClient(client: ClientDataRoomEntity)
}