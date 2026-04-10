package com.example.bancamovil.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_entity")
class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val cardId: Int = 0,
    val userId: Int,
    val cardNumber: String,
    val cardHolderName: String,
    val cardMoney: Double = 0.0,
    val expiryDate: String,
    val cardType: String // Ejemplo: "VISA", "MASTERCARD"
)