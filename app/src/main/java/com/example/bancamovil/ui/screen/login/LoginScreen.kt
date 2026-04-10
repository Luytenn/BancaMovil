package com.example.bancamovil.ui.screen.login

import android.R.attr.onClick
import android.provider.Contacts
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bancamovil.R
import com.example.bancamovil.ui.components.AlertDialog
import com.example.bancamovil.ui.components.ForgotPasswordComponent
import com.example.bancamovil.ui.components.ProgressOverlay
import com.example.bancamovil.ui.components.UiState
import com.example.bancamovil.ui.components.UsernameComponent
import com.example.bancamovil.ui.navigation.Navigate
import com.example.bancamovil.ui.theme.BancaMovilTheme
import com.example.bancamovil.ui.theme.blue500
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(
        content = {
            LoginContent(it, navController, loginViewModel)
        },
    )
}

@Composable
fun LoginContent(paddingScaffold: PaddingValues = PaddingValues(), navController: NavController = rememberNavController(), viewModel: LoginViewModel) {

    val userUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsState()
    val username by viewModel.username.collectAsState()
    val scope = rememberCoroutineScope()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background),

        verticalArrangement = Arrangement.Center

    ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 26.dp, vertical = 0.dp)

            ) {


                Image(
                    modifier = Modifier
                        .fillMaxSize(1f),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Pager Image"
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen40)))

                Text(
                    text = "Usuario",
                    style = BancaMovilTheme.typography.PreloMediumGrayDisplay16,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen20)))

                val view = LocalView.current
                UsernameComponent(
                    value = username,
                    onValueChange = { text ->
                        scope.launch {
                            val filtered = text.filter { it.isLetterOrDigit() }
                            viewModel.onUsernameChanged(filtered)
                        }
                    },
                    keyboarOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .bringIntoViewRequester(bringIntoViewRequester)
                        .focusRequester(focusRequester)
                        .onGloballyPositioned { coordinates ->

                        }.onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                scope.launch {
                                    bringIntoViewRequester.bringIntoView()
                                }
                            }
                        }
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen20)))


                Text(
                    text = "Contraseña",
                    style = BancaMovilTheme.typography.PreloMediumGrayDisplay16,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen20)))

                ForgotPasswordComponent(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .bringIntoViewRequester(bringIntoViewRequester)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                scope.launch {
                                    bringIntoViewRequester.bringIntoView()
                                }
                            }
                        },
                    value = password,
                    onValueChange = { pass ->
                        scope.launch {
                            val filtered = pass.filter { it.isLetterOrDigit() }
                            viewModel.onPassowrdChanged(filtered)
                        }
                    },
                    passwordVisible = passwordVisible,
                    returnPassVisible = { passwordVisible = it },
                    keyboarOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions.Default
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen10)))

                Box(Modifier.padding(horizontal = 24.dp)) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(26.dp)
                            ,
                        colors =
                            ButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Set the background color
                                contentColor = blue500,
                                disabledContentColor = Color.Unspecified,
                                disabledContainerColor = colorResource(R.color.bp_buttonPrimaryDisabled)),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            scope.launch {
                                viewModel.AuthLogin(username, password)
                            }
                        }
                    ) {
                        Text(text = "Ingresar",
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            style = BancaMovilTheme.typography.PreloMediumGrayDisplay14,
                            color = colorResource(R.color.white)
                        )
                    }
                }
        }
    }

    StateHandler(
        state = userUiState,
        onSuccess = { event ->
            when (event) {
                is LoginEvent.SuccessLogin -> {
                    val userId = event.user.id
                    navController.navigate(Navigate.Screen.AccountScreen.route + "/${userId}")
                }
                else -> {}
            }
        },
        onError = {

        },
        onLoading = { ProgressOverlay()},
        onEmpty = {
            AlertDialog(
                title = "Error",
                text = (userUiState as UiState.Empty).message,
                buttonText = "Aceptar")
                {
                viewModel.setIdle()
            }
        },
        onIdle = {

        }

    )

}

@Composable
fun <T> StateHandler(
    state: UiState<T>,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (T) -> Unit,
    onLoading: @Composable () -> Unit = {  },
    onEmpty: @Composable (String) -> Unit = {  },
    onIdle: @Composable () -> Unit = {}
) {
    when (state) {
        is UiState.Loading -> onLoading()
        is UiState.Success -> onSuccess(state.data)
        is UiState.Error -> onError(state.data)
        is UiState.Empty -> onEmpty(state.message)
        is UiState.Idle -> onIdle()
        else -> {}
    }
}
