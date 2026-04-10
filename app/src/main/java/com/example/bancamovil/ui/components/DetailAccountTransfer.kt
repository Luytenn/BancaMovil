package com.example.bancamovil.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bancamovil.R
import com.example.bancamovil.domain.model.Transfer
import com.example.bancamovil.ui.theme.BancaMovilTheme


@Composable
fun DetailAccountTransferItem(
    transferData: Transfer
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = transferData.destinationAccount,
                    style = BancaMovilTheme.typography.PreloMediumBlackDisplay16,
                )
                Text(
                    text = transferData.timestamp.toString(),
                    style = BancaMovilTheme.typography.PreloMediumBlackDisplay14
                    ,
                )
            }

            Text(text = "S/."+transferData.amount.toString(),
                style = BancaMovilTheme.typography.PreloMediumGrayDisplay16,
                color = colorResource(R.color.black)
            )

        }



    }

}