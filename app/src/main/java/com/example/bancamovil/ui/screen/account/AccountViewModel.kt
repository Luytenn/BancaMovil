package com.example.bancamovil.ui.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.domain.model.Users
import com.example.bancamovil.domain.use_case.GetCardUserUseCase
import com.example.bancamovil.ui.components.UiState
import com.example.bancamovil.ui.screen.login.LoginEvent
import com.example.bancamovil.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    val getCardUserUseCase: GetCardUserUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<AccountEvent>>(UiState.Idle)
    val uiState: StateFlow<UiState<AccountEvent>> = _uiState

    private val _listCard = MutableStateFlow<List<Card>>(emptyList())
    val listCard: StateFlow<List<Card>> = _listCard

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message


    fun getCardByUser(userId: Int) {
        viewModelScope.launch {
            getCardUserUseCase(userId).onEach { send ->
                when(send){
                    is ResultWrapper.Loading -> {
                        _uiState.value = UiState.Loading
                    }
                    is ResultWrapper.GenericError -> {
                        _uiState.value = UiState.Error(AccountEvent.ErrorAccount(send.message.toString()))
                        _message.value = send.message.toString()
                    }
                    is ResultWrapper.Success -> {
                        _uiState.value = UiState.Success(AccountEvent.SuccessAccount(send.data!!))
                        _listCard.value = send.data
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }

    fun pullRefreshUpdateCard(userId: Int) {
        viewModelScope.launch {
            getCardUserUseCase(userId).onEach { send ->
                when(send){
                    is ResultWrapper.GenericError -> {
                        _uiState.value = UiState.Error(AccountEvent.ErrorAccount("No se han podido cargar las cuentas, inténtelo de nuevo"))
                        _message.value = send.message.toString()
                    }
                    is ResultWrapper.Success -> {
                        _uiState.value = UiState.Success(AccountEvent.SuccessAccount(send.data!!))
                        _uiState.value = UiState.Error(AccountEvent.ErrorAccountPullToRefresh("No se han podido cargar las cuentas, inténtelo de nuevo"))
                        _listCard.value = send.data
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }

    fun setIdle() {
        _uiState.value = UiState.Idle
    }

}

sealed class AccountEvent {
    data class SuccessAccount(val listCard: List<Card>) : AccountEvent()
    data class ErrorAccount(val message: String): AccountEvent()
    data class ErrorAccountPullToRefresh(val message: String): AccountEvent()
    object Loading: AccountEvent()
}