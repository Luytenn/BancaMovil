package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ObserveSessionUseCase(
    private val sessionManager: SessionManager
) {

    operator fun invoke(): Flow<Boolean> = flow {
        while (true) {
            delay(1000)
            if (sessionManager.isSessionActive()) {
                emit(sessionManager.isSessionExpired())
            }
        }
    }
}