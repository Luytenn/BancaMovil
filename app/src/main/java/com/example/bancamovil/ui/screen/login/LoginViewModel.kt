package com.example.bancamovil.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bancamovil.data.SessionManager
import com.example.bancamovil.data.source.local.CardEntity
import com.example.bancamovil.data.source.local.TransferEntity
import com.example.bancamovil.data.source.local.UserEntity
import com.example.bancamovil.domain.model.Users
import com.example.bancamovil.domain.use_case.AuthLoginUserCase
import com.example.bancamovil.domain.use_case.InsertUserUseCase
import com.example.bancamovil.domain.use_case.SaveCardUseCase
import com.example.bancamovil.domain.use_case.SaveTransferUseCase
import com.example.bancamovil.domain.use_case.ValidateEmptyTable
import com.example.bancamovil.domain.use_case.session.SaveSessionUseCase
import com.example.bancamovil.ui.components.UiState
import com.example.bancamovil.util.InputFilter
import com.example.bancamovil.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Int
import kotlin.text.matches

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authLoginUserCase: AuthLoginUserCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val saveCardUseCase: SaveCardUseCase,
    private val saveTransferUseCase: SaveTransferUseCase,
    private val ValidateEmptyTable: ValidateEmptyTable,
    private val saveSessionUseCase: SaveSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<LoginEvent>>(UiState.Idle)
    val uiState: StateFlow<UiState<LoginEvent>> = _uiState
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private var _showUserSuccess = MutableSharedFlow<UserEntity>()
    var showUserSuccess: SharedFlow<UserEntity> = _showUserSuccess

    init {
        viewModelScope.launch {
            val resEmptyTable = ValidateEmptyTable.invoke()
            if (resEmptyTable) {
                initializeData()
             }
        }
    }

    suspend fun initializeData() {

            insertUserUseCase.invoke(
                listOf(
                    UserEntity(
                        id = 1,
                        fullName = "Juan Perez",
                        username = "userTest1",
                        passwordHash = "passTest1"
                    ),
                    UserEntity(
                        id = 2,
                        fullName = "Maria Lopez",
                        username = "User@test",
                        passwordHash = "TestPass_"
                    ),
                    UserEntity(
                        id = 3,
                        fullName = "Carlos Ruiz",
                        username = "user123&",
                        passwordHash = "123456"
                    )
                )
            )

            saveCardUseCase.invoke(
                listOf(
                    CardEntity(
                        userId = 1,
                        cardNumber = "111111111111",
                        cardHolderName = "Juan Perez",
                        cardMoney = 23222.0,
                        expiryDate = "12/25",
                        cardType = "Credito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "123123223123",
                        cardHolderName = "Juan Perez",
                        cardMoney = 112312.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "42415123123",
                        cardHolderName = "Juan Perez",
                        cardMoney = 123123.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "34314134134",
                        cardHolderName = "Juan Perez",
                        cardMoney = 12312.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "1231233513",
                        cardHolderName = "Juan Perez",
                        cardMoney = 123122.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "3413414431",
                        cardHolderName = "Juan Perez",
                        cardMoney = 434112.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "3414134341134",
                        cardHolderName = "Juan Perez",
                        cardMoney = 123212.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "134131351351",
                        cardHolderName = "Juan Perez",
                        cardMoney = 231122.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "1413414134134",
                        cardHolderName = "Juan Perez",
                        cardMoney = 123123.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "1341235232",
                        cardHolderName = "Juan Perez",
                        cardMoney = 12312.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "123123123123",
                        cardHolderName = "Juan Perez",
                        cardMoney = 134134.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "123123123123",
                        cardHolderName = "Juan Perez",
                        cardMoney = 12315.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "1314143314",
                        cardHolderName = "Juan Perez",
                        cardMoney = 23252.31,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    CardEntity(
                        userId = 1,
                        cardNumber = "13413413413",
                        cardHolderName = "Juan Perez",
                        cardMoney = 23313.0,
                        expiryDate = "12/26",
                        cardType = "Debito"
                    ),
                    // USER 2
                    CardEntity(
                        userId = 2,
                        cardNumber = "13413431413",
                        cardHolderName = "Maria Lopez",
                        cardMoney = 7502.0,
                        expiryDate = "11/25",
                        cardType = "Credito"
                    ),
                    CardEntity(
                        userId = 2,
                        cardNumber = "222222222222",
                        cardHolderName = "Maria Lopez",
                        cardMoney = 2000.0,
                        expiryDate = "10/26",
                        cardType = "Debito"
                    ),

                    // USER 3
                    CardEntity(
                        userId = 3,
                        cardNumber = "333333333331",
                        cardHolderName = "Carlos Ruiz",
                        cardMoney = 10000.0,
                        expiryDate = "01/27",
                        cardType = "Credito"
                    ),
                    CardEntity(
                        userId = 3,
                        cardNumber = "333333333332",
                        cardHolderName = "Carlos Ruiz",
                        cardMoney = 1500.0,
                        expiryDate = "02/27",
                        cardType = "Debito"
                    )
                )
            )
            saveTransferUseCase.invoke(
                listOf(
                    TransferEntity(cardId = 1, userId = 1, amount =  50.0, destinationAccount =  "YAPE 999", typeTransfer =  "YAPE", timestamp =  System.currentTimeMillis()),
                    TransferEntity(cardId = 1, userId = 1, amount =  100.0, destinationAccount =  "YAPE 999", typeTransfer =  "YAPE", timestamp =  System.currentTimeMillis()),
                    TransferEntity(cardId=  2,  userId = 1, amount = 160.0, destinationAccount = "PLIN 888",  typeTransfer = "PLIN", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId=  2,  userId = 1, amount = 1530.0, destinationAccount = "PLIN 888",  typeTransfer = "PLIN", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId=  2,  userId = 1, amount = 2502.0, destinationAccount = "PLIN 888",  typeTransfer = "PLIN", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId=  2,  userId = 1, amount = 38.0, destinationAccount = "PLIN 888",  typeTransfer = "PLIN", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId=  2,  userId = 1, amount = 257.0, destinationAccount = "PLIN 888",  typeTransfer = "PLIN", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId=  2,  userId = 1, amount = 124.0, destinationAccount = "PLIN 888",  typeTransfer = "PLIN", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId = 3, userId =  2,amount = 123.0, destinationAccount = "CARD EXT", typeTransfer = "CARD", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId = 4, userId =  2,amount = 75.0, destinationAccount = "SHOP", typeTransfer = "CARD", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId = 5, userId =  3,amount = 200.0, destinationAccount =  "RENT", typeTransfer = "YAPE", timestamp = System.currentTimeMillis()),
                    TransferEntity(cardId = 5, userId =  3,amount = 345.0, destinationAccount =  "RENT", typeTransfer = "YAPE", timestamp = System.currentTimeMillis()),
                    )
            )
    }

     fun onUsernameChanged(username: String) {
        if (username.matches(Regex("^[a-zA-Z0-9@._&-]{0,30}$"))) {
            _username.value = username
        }
    }

     fun onPassowrdChanged(password: String) {
        if (password.matches(Regex("^[a-zA-Z0-9@._&-]{0,30}$"))) {
            _password.value = password
        }

    }

    fun AuthLogin(username:String, password: String) {
         viewModelScope.launch {
             authLoginUserCase(username, password).onEach { send ->
                 when(send){
                     is ResultWrapper.Loading -> {
                         _uiState.value = UiState.Loading
                     }
                     is ResultWrapper.GenericError -> {
                         _uiState.value = UiState.Empty(send.message.toString())
                     }
                     is ResultWrapper.Success -> {
                         saveSessionUser(send.data?.id?:0)
                         _uiState.value = UiState.Success(LoginEvent.SuccessLogin(send.data!!))
                     }
                     else -> {}
                 }
             }.launchIn(viewModelScope)
         }

    }

    suspend fun saveSessionUser(userId: Int){
        saveSessionUseCase.invoke("idUserKey",userId)
    }

    fun setIdle() {
        _uiState.value = UiState.Idle
    }

}


sealed class LoginEvent {
    data class SuccessLogin(val user: Users) : LoginEvent()
    data class ErrorUser(val message: String): LoginEvent()
    object Loading: LoginEvent()
}