package com.example.bancamovil.domain.repository

interface UserSessionRepository {
    suspend fun saveUserId(key: String, value: Int)

    suspend fun getUserId(key: String): Int?

    suspend fun clearAll()
}