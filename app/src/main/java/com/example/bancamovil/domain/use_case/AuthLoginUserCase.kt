package com.example.bancamovil.domain.use_case

import com.example.bancamovil.data.source.local.UserEntity
import com.example.bancamovil.data.source.local.toUsers
import com.example.bancamovil.domain.model.Users
import com.example.bancamovil.domain.repository.BancaRepository
import com.example.bancamovil.util.ResultWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Timer
import javax.inject.Inject

class AuthLoginUserCase @Inject constructor(
    private val bancaRepository: BancaRepository,
) {
    suspend operator fun invoke(username: String, password: String): Flow<ResultWrapper<Users>> {
        return flow {
            try {
                emit(ResultWrapper.Loading())
                delay(3000)
                val res = bancaRepository.getUserByUsernamePassword(username,password).toUsers()
                if (res.fullName.isNotBlank()) {
                    emit(ResultWrapper.Success(res))
                } else {
                    emit(ResultWrapper.GenericError("Usuario y/o contraseña incorrectos"))
                }

            } catch (e:Exception) {
                emit(ResultWrapper.GenericError(message = "Usuario y/o contraseña incorrectos"))
            }
        }
    }
}