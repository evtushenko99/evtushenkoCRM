package com.evtushenko.crm.data

import com.evtushenko.crm.data.room.ClientsDao
import com.evtushenko.crm.models.data.ClientDataRoomEntity

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 10.02.2024
 */
class ClientsLocalDataStore(private val dao: ClientsDao) {

    suspend fun insert(client: ClientDataRoomEntity) {
        dao.insertClient(client)
    }

    suspend fun getAllClient():List<ClientDataRoomEntity> = dao.getAll()
}