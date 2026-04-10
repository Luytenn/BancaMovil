package com.example.bancamovil.ui.components

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.ui.theme.BancaMovilTheme

@Composable
fun AccountListItem(
    cardValue: Card,
    navigateToDetail: (userId:Int, cardId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        onClick = {
            navigateToDetail(cardValue.userId, cardValue.cardId)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
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
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = cardValue.cardType,
                        style = BancaMovilTheme.typography.PreloMediumBlackDisplay18,
                        )

                    Text(
                        text = "S/." + cardValue.cardMoney.toString(),
                        style = BancaMovilTheme.typography.PreloMediumBlackDisplay28,
                    )

                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    tint = Color(0xFF05BE50),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                )


            }


        }
    }
}