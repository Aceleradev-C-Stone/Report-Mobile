package com.upco.report.data

import com.upco.report.data.remote.mapper.UserMapper
import com.upco.report.data.remote.model.LoginRequest
import com.upco.report.data.remote.model.RegisterRequest
import com.upco.report.data.remote.source.RemoteDataSource
import com.upco.report.data.utils.TokenManager
import com.upco.report.domain.entities.User
import com.upco.report.domain.repository.UserRepository
import com.upco.report.domain.responses.ResultRemote
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val tokenManager: TokenManager
): UserRepository {

    override fun login(email: String, password: String): Flow<ResultRequired<User>> {
        return flow {
            val request = LoginRequest(email, password)
            emit(loginRemote(request))
        }
    }

    override fun register(
        name: String,
        email: String,
        password: String
    ): Flow<ResultRequired<User>> {
        return flow {
            val request = RegisterRequest(name, email, password)
            emit(registerRemote(request))
        }
    }

    private suspend fun loginRemote(request: LoginRequest): ResultRequired<User> {
        val result = remoteDataSource.login(request)
        return when (result) {
            is ResultRemote.Success -> {
                val token = result.response.loginPayload.token
                tokenManager.saveToken(token)
                val mappedUser = UserMapper.map(result.response.loginPayload.user)
                ResultRequired.Success(result = mappedUser)
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(result.throwable)
            }
        }
    }

    private suspend fun registerRemote(request: RegisterRequest): ResultRequired<User> {
        val result = remoteDataSource.register(request)
        return when (result) {
            is ResultRemote.Success -> {
                val mappedUser = UserMapper.map(result.response.user)
                ResultRequired.Success(result = mappedUser)
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(result.throwable)
            }
        }
    }
}