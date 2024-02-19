package com.evtushenko.crm.presentation.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.evtushenko.crm.domain.ClientsRepository
import com.evtushenko.crm.models.ClientOperationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientsViewModel(private val repository: ClientsRepository) : ViewModel() {

    private val _uiResult: MutableStateFlow<ClientUiState> =
        MutableStateFlow(ClientUiState.Idle)

    val uiResult: StateFlow<ClientUiState> = _uiResult

    fun getClients() {
        _uiResult.value = ClientUiState.Loading(true)
        viewModelScope.launch                                                                                                                                                               {
            when (val result = repository.getClients()) {
                is ClientOperationResult.Error -> {
                    _uiResult.value = ClientUiState.Loading(false)
                    _uiResult.value = ClientUiState.Error(result.message)
                }

                is ClientOperationResult.Success -> {
                    _uiResult.value = ClientUiState.Loading(false)
                    _uiResult.value = ClientUiState.Success(result.clients)
                }
            }
        }
    }


    class Factory(private val repository: ClientsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ClientsViewModel(repository) as T
        }
    }
}