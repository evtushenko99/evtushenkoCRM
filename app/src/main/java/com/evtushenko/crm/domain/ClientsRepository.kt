package com.evtushenko.crm.domain

import com.evtushenko.crm.models.ClientItem
import com.evtushenko.crm.models.ClientOperationResult

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
interface ClientsRepository {
    suspend fun getClients(): ClientOperationResult

    suspend fun deleteClient(clientItem: ClientItem)
}