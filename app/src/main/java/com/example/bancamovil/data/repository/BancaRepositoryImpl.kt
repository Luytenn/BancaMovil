package com.example.bancamovil.data.repository

import com.example.bancamovil.data.source.local.BancaMovilDao
import com.example.bancamovil.data.source.local.CardEntity
import com.example.bancamovil.data.source.local.TransferEntity
import com.example.bancamovil.data.source.local.UserEntity
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.domain.repository.BancaRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BancaRepositoryImpl @Inject constructor(
    private val dao: BancaMovilDao,
    private val ioDispatcher: CoroutineDispatcher
): BancaRepository {
    override suspend fun getUserByUsernamePassword(username: String, password: String): UserEntity {
        return withContext(ioDispatcher) {
            dao.getUserByCredentials(username, password)
        }
    }

    override suspend fun getCardByUsertId(userId: Int): List<CardEntity> {
        return withContext(ioDispatcher) {
            dao.getCardByUserId(userId)
        }
    }

    override suspend fun getTransferList(
        userId: Int,
        cardId: Int
    ): List<TransferEntity> {
        return withContext(ioDispatcher) {
            dao.getTransferList(userId, cardId)
        }
    }

    override suspend fun getCardSelected(
        userId: Int,
        cardId: Int
    ): CardEntity {
        return withContext(ioDispatcher) {
            dao.getCardSelected(userId, cardId)
        }
    }
    override suspend fun saveUser(user: List<UserEntity>) {
        return withContext(ioDispatcher) {
            dao.saveUser(user)
        }
    }

    override suspend fun saveCard(card: List<CardEntity>) {
        return withContext(ioDispatcher) {
            dao.saveCard(card)
        }
    }

    override suspend fun saveTransfer(transfer: List<TransferEntity>) {
        return withContext(ioDispatcher) {
            dao.saveTransfer(transfer)
        }
    }

    override suspend fun validateEmptyTable(): Boolean {
        return withContext(ioDispatcher) {
            dao.validateEmptyUser()
        }
    }
}