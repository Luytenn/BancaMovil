package com.example.bancamovil.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transfer_entity")
class TransferEntity(
    @PrimaryKey(autoGenerate = true) val transferId: Int = 0,
    val cardId: Int = 0,
    val userId: Int = 0,
    val amount: Double,
    val destinationAccount : String,
    val typeTransfer: String,
    val timestamp: Long
)