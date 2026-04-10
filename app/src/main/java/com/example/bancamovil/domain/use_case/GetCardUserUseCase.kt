package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.source.local.CardEntity
import com.example.bancamovil.data.source.local.UserEntity
import com.example.bancamovil.data.source.local.toCard
import com.example.bancamovil.data.source.local.toDomainCardList
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.domain.repository.BancaRepository
import com.example.bancamovil.util.ResultWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCardUserUseCase @Inject constructor(
    private val bancaRepository: BancaRepository,
) {
     operator fun invoke(userId: Int): Flow<ResultWrapper<List<Card>>> {
        return flow {
            try {
                emit(ResultWrapper.Loading())
                delay(3000)
                val card = bancaRepository.getCardByUsertId(userId).toDomainCardList()
                when {
                    card.isEmpty() -> {
                        emit(ResultWrapper.GenericError("No cuentas con tarjetas"))
                    }
                    else -> {
                        emit(ResultWrapper.Success(card))
                    }
                }

            } catch (e:Exception) {
                emit(ResultWrapper.GenericError(message = "Ha ocurrido un error, vuelve a intentarlo."))
            }
        }
    }
}