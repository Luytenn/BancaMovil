package com.example.bancamovil.ui.screen.detailAccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.bancamovil.ui.components.AccountListItem
import com.example.bancamovil.ui.components.AlertDialog
import com.example.bancamovil.ui.components.DetailAccountTransferItem
import com.example.bancamovil.ui.components.ProgressOverlay
import com.example.bancamovil.ui.components.TopAppBar
import com.example.bancamovil.ui.components.UiState
import com.example.bancamovil.ui.navigation.Navigate
import com.example.bancamovil.ui.screen.account.AccountContent
import com.example.bancamovil.ui.screen.account.AccountEvent
import com.example.bancamovil.ui.screen.account.AccountList
import com.example.bancamovil.ui.screen.account.AccountViewModel
import com.example.bancamovil.ui.screen.login.StateHandler
import com.example.bancamovil.ui.theme.BancaMovilTheme
import com.example.bancamovil.ui.theme.blue500

@Composable
fun DetailAccountScreen(
    navController: NavHostController,
    detailAccountViewModel: DetailAccountViewModel = hiltViewModel(),
    userId: Int,
    cardId: Int,
) {
    Scaffold(
        topBar = {
            TopAppBar(titleScreen = "Detalle Producto"
            ) {
                navController.popBackStack()
            }
        },
        content = { paddingValues ->
            DetailAccountContent(modifier = Modifier.padding(paddingValues), navController, detailAccountViewModel, userId, cardId)
        },
    )
}

@Composable
fun DetailAccountContent(
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    viewModel: DetailAccountViewModel,
    userId: Int,
    cardId: Int
) {
    val detailAccountState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.getCardSelected(userId, cardId)
        viewModel.getTransferList(userId, cardId)
    }

    Column(
        modifier = modifier
    ) {
        CardDetail(viewModel)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen16)))

        Text(text = "Movimientos",
            modifier = Modifier.padding(horizontal = 18.dp),
            style = BancaMovilTheme.typography.PreloMediumBlackDisplay20,
            color = colorResource(R.color.black)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen16)))

        accountTransferList(viewModel)

    }

    StateHandler(
        state = detailAccountState,
        onSuccess = { event ->
            when (event) {
                is AccountDetailEvent.SuccessAccountDetail -> {
                }
                else -> {}
            }
        },
        onError = { event ->
            when (event) {
                is AccountDetailEvent.ErrorAccountDetail -> {
                    AlertDialog(
                        title = "Error",
                        text = event.message,
                        buttonText = "Reintentar") {
                    }
                }
                else -> {}
            }

        },
        onLoading = { ProgressOverlay()},
        onEmpty = {

        },
        onIdle = {
        }
    )

}


@Composable
fun CardDetail(
    viewModel: DetailAccountViewModel
) {

    val cardSelected by viewModel.cardSelected.collectAsStateWithLifecycle()

    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = com.example.bancamovil.R.drawable.money_pig),
                    contentDescription = "Pager Image"
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                ) {
                    Text(
                        text =  cardSelected.cardType,
                        style = BancaMovilTheme.typography.PreloMediumBlackDisplay16,
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen5)))
                    Text(
                        text = "S/."+cardSelected.cardMoney.toString(),
                        style = BancaMovilTheme.typography.PreloMediumBlackDisplay28,
                    )
                }

            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen16)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Número de cuenta",
                        style = BancaMovilTheme.typography.PreloMediumBlackDisplay18,
                    )
                    Text(
                        text = cardSelected.cardNumber,
                        style = BancaMovilTheme.typography.PreloMediumBlackDisplay18,
                    )

                }

                Box(Modifier.padding(horizontal = 0.dp)) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp)
                        ,
                        colors =
                            ButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Set the background color
                                contentColor = blue500,
                                disabledContentColor = Color.Unspecified,
                                disabledContainerColor = colorResource(R.color.bp_buttonPrimaryDisabled)),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                        }
                    ) {
                        Text(text = "Copiar",
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            style = BancaMovilTheme.typography.PreloMediumGrayDisplay16,
                            color = colorResource(R.color.white)
                        )
                    }
                }

            }

        }

    }

}

@Composable
fun accountTransferList(
    viewModel: DetailAccountViewModel
) {

       val listTransfer by viewModel.transferList.collectAsStateWithLifecycle()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(listTransfer) {
                DetailAccountTransferItem(it)
            }
        }

}