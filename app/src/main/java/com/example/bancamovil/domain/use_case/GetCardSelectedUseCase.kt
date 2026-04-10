package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.source.local.toCard
import com.example.bancamovil.data.source.local.toDomainCardList
import com.example.bancamovil.domain.model.Card
import com.example.bancamovil.domain.repository.BancaRepository
import com.example.bancamovil.util.ResultWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCardSelectedUseCase @Inject constructor(
    private val bancaRepository: BancaRepository,
) {
    operator fun invoke(userId: Int, cardId: Int): Flow<ResultWrapper<Card>> {
        return flow {
            try {
                emit(ResultWrapper.Loading())
                delay(3000)
                val card = bancaRepository.getCardSelected(userId, cardId).toCard()
                when {
                    card.cardId == 0 -> {
                        emit(ResultWrapper.GenericError("No Se pudo encontrar la tarjeta"))
                    }
                    else -> {
                        emit(ResultWrapper.Success(card))
                    }
                }

            } catch (e:Exception) {
                emit(ResultWrapper.GenericError(message = "No Se pudo encontrar la tarjeta"))
            }
        }
    }
}