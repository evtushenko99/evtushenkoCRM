package com.evtushenko.crm.models

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
sealed class ClientOperationResult {

    data class Success(val clients: List<ClientItem>) : ClientOperationResult()

    data class Error(
        val message: String
    ) : ClientOperationResult()
}