package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.source.local.UserEntity
import com.example.bancamovil.domain.repository.BancaRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val bancaRepository: BancaRepository,
) {
    suspend operator fun invoke(user: List<UserEntity>) {
        return bancaRepository.saveUser(user)
    }
}