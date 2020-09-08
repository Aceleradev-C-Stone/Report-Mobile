package com.upco.report.domain.usecases

import com.upco.report.domain.entities.Log
import com.upco.report.domain.repository.LogsRepository
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CreateLogUseCase {

    fun execute(log: Log): Flow<ResultLog>

    sealed class ResultLog {
        data class Result(val log: Log): ResultLog()
        object Error: ResultLog()
    }
}

class CreateLogUseCaseImpl(
    private val repository: LogsRepository
): CreateLogUseCase {

    override fun execute(log: Log): Flow<CreateLogUseCase.ResultLog> {
        return repository.createLog(log)
            .map {
                when (it) {
                    is ResultRequired.Success -> {
                        CreateLogUseCase.ResultLog.Result(it.result)
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        CreateLogUseCase.ResultLog.Error
                    }
                }
            }
    }
}