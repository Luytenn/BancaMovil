package com.example.bancamovil.ui.screen.detailAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.domain.model.Transfer
import com.example.bancamovil.domain.use_case.GetCardSelectedUseCase
import com.example.bancamovil.domain.use_case.GetTransferUseCase
import com.example.bancamovil.ui.components.UiState
import com.example.bancamovil.ui.screen.account.AccountEvent
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
class DetailAccountViewModel @Inject constructor(
    val getCardSelectedUseCase: GetCardSelectedUseCase,
    val getTransferUseCase: GetTransferUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<AccountDetailEvent>>(UiState.Idle)
    val uiState: StateFlow<UiState<AccountDetailEvent>> = _uiState

    private val _cardSelected = MutableStateFlow(Card())
    val cardSelected: StateFlow<Card> = _cardSelected

    private val _transferList = MutableStateFlow<List<Transfer>>(emptyList())
    val transferList: StateFlow<List<Transfer>> = _transferList

    fun getCardSelected(userId: Int, cardId: Int) {
        viewModelScope.launch {
            getCardSelectedUseCase(userId, cardId).onEach { send ->
                when(send){
                    is ResultWrapper.Loading -> {
                        _uiState.value = UiState.Loading
                    }
                    is ResultWrapper.GenericError -> {
                        _uiState.value = UiState.Empty(send.message.toString())
                    }
                    is ResultWrapper.Success -> {
                        _uiState.value = UiState.Success(AccountDetailEvent.SuccessAccountDetail(send.data!!))
                        _cardSelected.value = send.data
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getTransferList(userId: Int, cardId: Int) {
        viewModelScope.launch {
            getTransferUseCase(userId, cardId).onEach { send ->
                when(send){
                    is ResultWrapper.Loading -> {
                        _uiState.value = UiState.Loading
                    }
                    is ResultWrapper.GenericError -> {
                        _uiState.value = UiState.Empty(send.message.toString())
                    }
                    is ResultWrapper.Success -> {
                        _transferList.value = send.data!!
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

sealed class AccountDetailEvent {
    data class SuccessAccountDetail(val card: Card) : AccountDetailEvent()
    data class ErrorAccountDetail(val message: String): AccountDetailEvent()
    object Loading: AccountDetailEvent()
}