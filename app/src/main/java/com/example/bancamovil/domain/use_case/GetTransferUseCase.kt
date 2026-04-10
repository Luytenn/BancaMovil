package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.source.local.toDomainCardList
import com.example.bancamovil.data.source.local.toDomainTransferList
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.domain.model.Transfer
import com.example.bancamovil.domain.repository.BancaRepository
import com.example.bancamovil.util.ResultWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTransferUseCase @Inject constructor(
    private val bancaRepository: BancaRepository,
) {
    operator fun invoke(userId: Int, cardId: Int): Flow<ResultWrapper<List<Transfer>>> {
        return flow {
            try {
                emit(ResultWrapper.Loading())
                delay(3000)
                val transferList = bancaRepository.getTransferList(userId, cardId).toDomainTransferList()
                when {
                    transferList.isEmpty() -> {
                        emit(ResultWrapper.GenericError("No cuentas con transferencias"))
                    }
                    else -> {
                        emit(ResultWrapper.Success(transferList))
                    }
                }

            } catch (e:Exception) {
                emit(ResultWrapper.GenericError(message = "Error al mostrar las transferencias"))
            }
        }
    }
}