package com.example.bancamovil.domain.repository

import com.example.bancamovil.data.source.local.CardEntity
import com.example.bancamovil.data.source.local.TransferEntity
import com.example.bancamovil.data.source.local.UserEntity
import com.example.bancamovil.domain.model.Card

interface BancaRepository {
    suspend fun getUserByUsernamePassword(username: String, password: String): UserEntity

    suspend fun getCardByUsertId(userId: Int): List<CardEntity>

    suspend fun getTransferList(userId: Int, cardId: Int): List<TransferEntity>
    suspend fun getCardSelected(userId: Int, cardId: Int): CardEntity
    suspend fun saveUser(user: List<UserEntity>)
    suspend fun saveCard(card: List<CardEntity>)
    suspend fun saveTransfer(transfer: List<TransferEntity>)
    suspend fun validateEmptyTable(): Boolean
}

