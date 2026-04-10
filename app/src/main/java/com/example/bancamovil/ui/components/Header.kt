package com.example.bancamovil.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    text: String
) {
    Column(
        modifier = Modifier.padding
            (bottom = 20.dp, top = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp,
            )
        }
    }
}