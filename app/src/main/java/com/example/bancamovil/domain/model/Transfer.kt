package com.example.bancamovil.domain.model


data class Transfer(
    val transferId: Int = 0,
    val cardId: Int = 0,
    val userId: Int = 0,
    val amount: Double,
    val destinationAccount : String,
    val typeTransfer: String,
    val timestamp: Long
)