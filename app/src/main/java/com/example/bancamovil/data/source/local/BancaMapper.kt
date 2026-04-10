package com.example.bancamovil.data.source.local

import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.domain.model.Transfer
import com.example.bancamovil.domain.model.Users
import kotlin.Int

fun UserEntity.toUsers() = Users(
        id = id,
        fullName = fullName,
        username = username,
        passwordHash = passwordHash
)

fun CardEntity.toCard() = Card(
        cardId = cardId ,
        userId = userId,
        cardNumber = cardNumber,
        cardHolderName = cardHolderName,
        cardMoney = cardMoney,
        expiryDate = expiryDate,
        cardType = cardType
)

fun List<CardEntity>.toDomainCardList(): List<Card> {
        return this.map { entity ->
                Card(
                cardId = entity.cardId ,
                userId = entity.userId,
                cardNumber = entity.cardNumber,
                cardHolderName = entity.cardHolderName,
                cardMoney = entity.cardMoney,
                expiryDate = entity.expiryDate,
                cardType = entity.cardType
                )
        }
}

fun List<TransferEntity>.toDomainTransferList(): List<Transfer> {
        return this.map { entity ->
                Transfer(
                        transferId = entity.transferId,
                        cardId = entity.cardId,
                        userId = entity.userId,
                        amount = entity.amount,
                        destinationAccount = entity.destinationAccount,
                        typeTransfer = entity.typeTransfer,
                        timestamp = entity.timestamp
                )
        }
}

