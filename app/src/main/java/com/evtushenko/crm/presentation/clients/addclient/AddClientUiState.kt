package com.evtushenko.crm.presentation.clients.addclient

sealed class AddClientUiState {

    data object Idle: AddClientUiState()

    data class Loading(val show:Boolean) : AddClientUiState()

    data class ShowSnackBar(val message: String) : AddClientUiState()

    data object Close : AddClientUiState()
}