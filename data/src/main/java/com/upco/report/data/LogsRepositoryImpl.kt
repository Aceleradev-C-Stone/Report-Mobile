package com.upco.report.data

import com.upco.report.data.local.mapper.LogCacheMapper
import com.upco.report.data.local.source.LogsCacheDataSource
import com.upco.report.data.remote.mapper.LogMapper
import com.upco.report.data.remote.source.RemoteDataSource
import com.upco.report.domain.entities.Log
import com.upco.report.domain.repository.LogsRepository
import com.upco.report.domain.responses.ResultRemote
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

    class LogsRepositoryImpl(
        private val logsCacheDataSource: LogsCacheDataSource,
        private val remoteDataSource: RemoteDataSource
): LogsRepository {

    override fun getLogs(): Flow<ResultRequired<List<Log>>> {
        return logsCacheDataSource.getLogs()
            .map { cacheList ->
                val result = when {
                    cacheList.isEmpty() -> getLogsRemote()
                    else -> {
                        val logs = LogCacheMapper.map(cacheList)
                        ResultRequired.Success(logs)
                    }
                }

                result
            }
    }

    override fun createLog(log: Log): Flow<ResultRequired<Log>> {
        return flow {
            val remoteResult = remoteDataSource.createLog(LogMapper.mapToRequest(log))
            val result = when (remoteResult) {
                is ResultRemote.Success -> {
                    val mappedLog = LogMapper.map(remoteResult.response.log)
                    val cacheLog = LogCacheMapper.map(mappedLog)
                    logsCacheDataSource.insertData(cacheLog)
                    ResultRequired.Success(result = mappedLog)
                }
                is ResultRemote.ErrorResponse -> {
                    ResultRequired.Error(remoteResult.throwable)
                }
            }

            emit(result)
        }
    }

    private suspend fun getLogsRemote(): ResultRequired<List<Log>> {
        val result = remoteDataSource.getLogs()
        return when (result) {
            is ResultRemote.Success -> {
                val mappedList = LogMapper.map(result.response)
                val cacheList = LogCacheMapper.mapLogsToCache(mappedList)
                logsCacheDataSource.updateData(cacheList)
                ResultRequired.Success(result = mappedList)
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(result.throwable)
            }
        }
    }
}