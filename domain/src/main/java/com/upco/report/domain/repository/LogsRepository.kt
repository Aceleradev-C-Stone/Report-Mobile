package com.upco.report.domain.repository

import com.upco.report.domain.entities.Log
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface LogsRepository {
    fun getLogs(): Flow<ResultRequired<List<Log>>>
    fun createLog(log: Log): Flow<ResultRequired<Log>>
}