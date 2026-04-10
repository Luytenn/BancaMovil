package com.example.bancamovil.data.source.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_entity",
    indices = [
        Index(value = ["username"], unique = true),
        Index(value = ["passwordHash"], unique = true)
    ]
    )
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val username: String,
    val passwordHash: String,
)