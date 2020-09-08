package com.upco.report.domain.usecases

import com.upco.report.domain.entities.User
import com.upco.report.domain.repository.UserRepository
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LoginUseCase {

    fun execute(email: String, password: String): Flow<ResultUser>

    sealed class ResultUser {
        data class Result(val user: User): ResultUser()
        object Error: ResultUser()
    }
}

class LoginUseCaseImpl(
    private val repository: UserRepository
): LoginUseCase {

    override fun execute(email: String, password: String): Flow<LoginUseCase.ResultUser> {
        return repository.login(email, password)
            .map {
                when (it) {
                    is ResultRequired.Success -> {
                        LoginUseCase.ResultUser.Result(it.result)
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        LoginUseCase.ResultUser.Error
                    }
                }
            }
    }
}