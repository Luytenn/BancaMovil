package com.example.bancamovil.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BancaMovilDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(userEntity: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCard(cardEntity: List<CardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransfer(transferEntity: List<TransferEntity>)

    @Query("SELECT (SELECT COUNT(*) FROM user_entity) == 0")
    suspend fun validateEmptyUser(): Boolean

    @Query("SELECT * FROM user_entity WHERE username = :usuario AND passwordHash = :password LIMIT 1")
    suspend fun getUserByCredentials(usuario: String, password: String): UserEntity

    @Query("SELECT * FROM card_entity WHERE userId = :userId")
    suspend fun getCardByUserId(userId: Int): List<CardEntity>

    @Query("SELECT * FROM card_entity WHERE userId = :userId AND cardId = :cardId LIMIT 1")
    suspend fun getCardSelected(userId: Int, cardId: Int): CardEntity

    @Query("SELECT * FROM transfer_entity WHERE userId = :userId AND cardId = :cardId")
    suspend fun getTransferList(userId: Int, cardId: Int): List<TransferEntity>

}