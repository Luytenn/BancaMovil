package com.example.bancamovil.di

import android.app.Application
import androidx.room.Room
import com.example.bancamovil.data.repository.BancaRepositoryImpl
import com.example.bancamovil.data.source.local.BancaMovilDB
import com.example.bancamovil.domain.repository.BancaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun providerBancaDatabaseLocal(app: Application): BancaMovilDB {
        return Room.databaseBuilder(app, BancaMovilDB::class.java,BancaMovilDB.DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideBancaRepository(
        db: BancaMovilDB,
        @IoDispatcher ioCoroutine: CoroutineDispatcher
    ): BancaRepository {
        return BancaRepositoryImpl(db.bancaDao, ioCoroutine)
    }

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class IoDispatcher

}