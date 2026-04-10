package com.example.bancamovil.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserEntity::class,
        CardEntity::class,
        TransferEntity::class
    ],
    version = 17
)
//@TypeConverters(DataConverter::class)
abstract class BancaMovilDB : RoomDatabase() {
    abstract val bancaDao: BancaMovilDao
    companion object {
        const val DATABASE_NAME = "bancamovil_db"
    }
}