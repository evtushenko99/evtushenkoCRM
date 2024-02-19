package com.evtushenko.crm.presentation.clients.addclient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.evtushenko.crm.domain.AddClientsRepository
import com.evtushenko.crm.models.ClientAddEntity
import com.evtushenko.crm.models.AddClientOperationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddClientsViewModel(private val repository: AddClientsRepository) : ViewModel() {

    private val _uiResult: MutableStateFlow<AddClientUiState> =
        MutableStateFlow(AddClientUiState.Idle)

    val uiResult: StateFlow<AddClientUiState> = _uiResult

    fun addClient(newClient: ClientAddEntity) {
        viewModelScope.launch {
            _uiResult.value = AddClientUiState.Loading(true)
            when (val result = repository.addClient(newClient)) {
                is AddClientOperationResult.Error -> {
                    _uiResult.value = AddClientUiState.Loading(false)
                    _uiResult.value = AddClientUiState.ShowSnackBar(message = result.message)
                }

                AddClientOperationResult.Success -> {
                    _uiResult.value = AddClientUiState.Loading(false)
                    _uiResult.value =
                        AddClientUiState.ShowSnackBar(message = "Клиент Успешно добавлен")
                    _uiResult.value = AddClientUiState.Close
                }
            }
        }
    }

    class Factory(private val repository: AddClientsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddClientsViewModel(repository) as T
        }
    }
}