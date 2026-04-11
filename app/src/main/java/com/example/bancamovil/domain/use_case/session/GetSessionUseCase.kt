package com.example.bancamovil.domain.use_case.session

import com.example.bancamovil.domain.repository.UserSessionRepository
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val userSessionRepository: UserSessionRepository,
) {
    suspend operator fun invoke(key: String): Int? {
        return userSessionRepository.getUserId(key)
    }
}