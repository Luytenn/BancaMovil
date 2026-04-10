package com.example.bancamovil.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun AlertDialog(
    title: String,
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        // icon = { Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon") },
        title = { Text(text = title) },
        text = { Text(text = text) },
        confirmButton = {
            Button(
                onClick = {
                    onClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = buttonText)
            }
        },
        containerColor = Color.White,
        titleContentColor = Color.DarkGray,
        textContentColor = Color.DarkGray
    )

}