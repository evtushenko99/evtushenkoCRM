package com.evtushenko.crm.domain

import com.evtushenko.crm.models.ClientAddEntity
import com.evtushenko.crm.models.AddClientOperationResult

interface AddClientsRepository {

   suspend fun addClient(client: ClientAddEntity): AddClientOperationResult
}