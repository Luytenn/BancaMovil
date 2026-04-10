package com.example.bancamovil.domain.model


data class Users(
    val id: Int = 0,
    val fullName: String,
    val username: String,
    val passwordHash: String,
)