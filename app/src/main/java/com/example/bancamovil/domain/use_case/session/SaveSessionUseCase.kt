package com.example.bancamovil.domain.use_case.session

import com.example.bancamovil.domain.repository.UserSessionRepository
import javax.inject.Inject

class SaveSessionUseCase @Inject constructor(
    private val userSessionRepository: UserSessionRepository,
) {
    suspend operator fun invoke(key: String, value: Int) {
        return userSessionRepository.saveUserId(key, value)
    }
}