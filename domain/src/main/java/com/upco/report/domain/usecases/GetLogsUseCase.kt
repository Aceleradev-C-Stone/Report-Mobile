package com.upco.report.domain.usecases

import com.upco.report.domain.entities.Log
import com.upco.report.domain.repository.LogsRepository
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetLogsUseCase {

    fun execute(): Flow<ResultLogs>

    sealed class ResultLogs {
        data class Logs(val list: List<Log>): ResultLogs()
        object NoLogs: ResultLogs()
        object Error: ResultLogs()
    }
}

class GetLogsUseCaseImpl(
    private val repository: LogsRepository
): GetLogsUseCase {

    override fun execute(): Flow<GetLogsUseCase.ResultLogs> {
        return repository.getLogs()
            .map {
                when (it) {
                    is ResultRequired.Success -> {
                        when {
                            it.result.isEmpty() -> GetLogsUseCase.ResultLogs.NoLogs
                            else -> GetLogsUseCase.ResultLogs.Logs(it.result.reversed())
                        }
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        GetLogsUseCase.ResultLogs.Error
                    }
                }
            }
    }
}