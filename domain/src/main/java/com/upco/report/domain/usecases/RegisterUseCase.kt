package com.upco.report.domain.usecases

import com.upco.report.domain.entities.User
import com.upco.report.domain.repository.UserRepository
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface RegisterUseCase {

    fun execute(name: String, email: String, password: String): Flow<ResultUser>

    sealed class ResultUser {
        data class Result(val user: User): ResultUser()
        object Error: ResultUser()
    }
}

class RegisterUseCaseImpl(
    private val repository: UserRepository
): RegisterUseCase {

    override fun execute(
        name: String,
        email: String,
        password: String
    ): Flow<RegisterUseCase.ResultUser> {

        return repository.register(name, email, password)
            .map {
                when (it) {
                    is ResultRequired.Success -> {
                        RegisterUseCase.ResultUser.Result(it.result)
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        RegisterUseCase.ResultUser.Error
                    }
                }
            }
    }
}