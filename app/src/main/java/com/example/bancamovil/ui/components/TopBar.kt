package com.example.bancamovil.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bancamovil.ui.theme.BancaMovilTheme

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(name = "Light Mode", showBackground = true)
@Composable
fun TopAppBar(
    titleScreen: String,
    onClick: () -> Unit = {},
){
    androidx.compose.material3.TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.Transparent,
            actionIconContentColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        title = {
            Text(text = titleScreen,
                style = BancaMovilTheme.typography.PreloBoldBlackDisplay20,
                )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.padding(start = 15.dp),
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Go back"
                )
            }
        },
        actions = { }
    )

}