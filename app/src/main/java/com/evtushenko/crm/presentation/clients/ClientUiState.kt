package com.evtushenko.crm.presentation.clients

import com.evtushenko.crm.models.ClientItem

sealed class ClientUiState {

    data object Idle : ClientUiState()

    data class Loading(val show: Boolean) : ClientUiState()

    data class Error(val message: String) : ClientUiState()

    data class Success(val clients: List<ClientItem>) : ClientUiState()
}