package com.example.bancamovil.ui.screen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.ui.components.AccountListItem
import com.example.bancamovil.ui.components.AlertDialog
import com.example.bancamovil.ui.components.EmptyItem
import com.example.bancamovil.ui.components.ProgressOverlay
import com.example.bancamovil.ui.components.TopAppBar
import com.example.bancamovil.ui.components.UiState
import com.example.bancamovil.ui.navigation.Navigate
import com.example.bancamovil.ui.navigation.Navigate.Screen
import com.example.bancamovil.ui.screen.login.LoginEvent
import com.example.bancamovil.ui.screen.login.StateHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AccountScreen(
    navController: NavHostController,
    userId: Int,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(titleScreen = "Productos"
            ) {
                accountViewModel.clearSession()
                navController.navigate(Screen.LogiScreen.route)
            }
        },
        content = {
            AccountContent(it, navController, accountViewModel, userId)
        },
    )
}

@Composable
fun AccountContent(
    paddingScaffold: PaddingValues = PaddingValues(),
    navController: NavController = rememberNavController(),
    viewModel: AccountViewModel,
    userId: Int
) {

    val accountUiState by viewModel.uiState.collectAsStateWithLifecycle()

    AccountList(navController,viewModel, userId)

    LaunchedEffect(Unit) {
        viewModel.getCardByUser(userId)
    }

    StateHandler(
        state = accountUiState,
        onSuccess = { event ->
            when (event) {
                is AccountEvent.SuccessAccount -> {

                }
                is AccountEvent.ErrorAccountPullToRefresh -> {
                    AlertDialog(
                        title = "Error",
                        text = "Vuelve a intentarlo",
                        buttonText = "Aceptar") {
                        viewModel.setIdle()
                    }
                }
                else -> {}
            }
        },
        onError = { event ->
            when (event) {
                is AccountEvent.ErrorAccount -> {
                    AlertDialog(
                        title = "Error",
                        text = event.message,
                        buttonText = "Reintentar") {
                        viewModel.getCardByUser(userId)
                    }
                }
                is AccountEvent.ErrorAccountPullToRefresh -> {
                    AlertDialog(
                        title = "Error",
                        text = "Vuelve a intentarlo",
                        buttonText = "Aceptar") {
                        viewModel.setIdle()
                    }
                }
                else -> {}
            }

        },
        onLoading = { ProgressOverlay()},
        onEmpty = {
            AlertDialog(
                title = "Error",
                text =(accountUiState as UiState.Empty).message,
                buttonText = "Reintentar"
                ) {

            }
        },
        onIdle = {

        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountList(
    navController: NavController,
    viewModel: AccountViewModel,
    userId: Int,
) {


    val listCard by viewModel.listCard.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle()
    val pullToRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }


    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            isRefreshing = true
            delay(300L)
            viewModel.pullRefreshUpdateCard(userId)
            isRefreshing = false
        }
    }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            pullToRefreshState.startRefresh()
        } else {
            pullToRefreshState.endRefresh()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                if (listCard.isNotEmpty()) {
                    items(listCard) {
                        AccountListItem(it,navigateToDetail = { userId, cardId -> navController.navigate(Navigate.Screen.AccountDetailScreen.route+ "/${userId}" + "/${cardId}") })
                    }
                } else {
                    item {
                        EmptyItem("No se pudo obtener las cuentas")
                    }
                }
            }

            PullToRefreshContainer(state = pullToRefreshState,
                modifier = Modifier.align(Alignment.TopCenter))

        }
    }



}

