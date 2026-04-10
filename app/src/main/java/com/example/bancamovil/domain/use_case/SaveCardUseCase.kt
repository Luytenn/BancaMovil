package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.source.local.CardEntity
import com.example.bancamovil.data.source.local.UserEntity
import com.example.bancamovil.domain.repository.BancaRepository
import javax.inject.Inject

class SaveCardUseCase @Inject constructor(
    private val bancaRepository: BancaRepository,
) {
    suspend operator fun invoke(card: List<CardEntity>) {
        return bancaRepository.saveCard(card)
    }
}