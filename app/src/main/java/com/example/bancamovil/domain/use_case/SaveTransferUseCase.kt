package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.source.local.CardEntity
import com.example.bancamovil.data.source.local.TransferEntity
import com.example.bancamovil.domain.repository.BancaRepository
import javax.inject.Inject

class SaveTransferUseCase @Inject constructor(
    private val bancaRepository: BancaRepository,
) {
    suspend operator fun invoke(transfer: List<TransferEntity>) {
        return bancaRepository.saveTransfer(transfer)
    }
}