package com.example.bancamovil.domain.use_case.session

import com.example.bancamovil.domain.repository.UserSessionRepository
import javax.inject.Inject

class ClearSessionUseCase @Inject constructor(
    private val userSessionRepository: UserSessionRepository,
) {
    suspend operator fun invoke() {
        return userSessionRepository.clearAll()
    }
}