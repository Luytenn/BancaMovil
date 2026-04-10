package com.example.bancamovil.domain.model

data class Card(
    val cardId: Int = 0,
    val userId: Int = 0,
    val cardNumber: String = "",
    val cardHolderName: String = "",
    val cardMoney: Double = 0.0,
    val expiryDate: String = "",
    val cardType: String = ""
)