package com.evtushenko.crm.models

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
sealed class AddClientOperationResult {

    data object Success : AddClientOperationResult()

    data class Error(
        val message: String
    ) : AddClientOperationResult()
}