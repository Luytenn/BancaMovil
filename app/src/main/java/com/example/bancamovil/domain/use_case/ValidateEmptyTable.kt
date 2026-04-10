package com.example.bancamovil.domain.use_case

import com.example.bancamovil.domain.repository.BancaRepository
import javax.inject.Inject

class ValidateEmptyTable @Inject constructor(
    private val repository: BancaRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.validateEmptyTable()
    }
}