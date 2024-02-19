package com.evtushenko.crm.data

import com.evtushenko.crm.domain.AddClientsRepository
import com.evtushenko.crm.domain.ClientsRepository
import com.evtushenko.crm.models.AddClientOperationResult
import com.evtushenko.crm.models.ClientAddEntity
import com.evtushenko.crm.models.ClientGender.Companion.toGender
import com.evtushenko.crm.models.ClientItem
import com.evtushenko.crm.models.ClientOperationResult
import com.evtushenko.crm.models.data.ClientDataRoomEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ClientsRepositoryImpl(
    private val localStore: ClientsLocalDataStore,
    private val remoteDataStore: ClientsRemoteDataStore
) : AddClientsRepository, ClientsRepository {
    override suspend fun addClient(client: ClientAddEntity): AddClientOperationResult {
        withContext(Dispatchers.IO) {
            delay(2000L)
            localStore.insert(
                client = ClientDataRoomEntity(
                    client.hashCode(),
                    client.name,
                    client.lastName,
                    client.phone,
                    client.lastName,
                    client.gender.name
                )
            )
        }
        return AddClientOperationResult.Success
    }

    override suspend fun getClients(): ClientOperationResult {
        val clients = withContext(Dispatchers.IO) {
            delay(2000L)
            localStore.getAllClient()
        }.map { ClientItem(it.id, it.firstName, it.lastName, it.gender.toGender()) }

        return ClientOperationResult.Success(clients)
    }

    override suspend fun deleteClient(clientItem: ClientItem) {
        TODO("Not yet implemented")
    }
}