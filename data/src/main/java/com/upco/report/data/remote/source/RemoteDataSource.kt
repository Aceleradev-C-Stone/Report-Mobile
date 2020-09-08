package com.upco.report.data.remote.source

import com.upco.report.data.remote.model.*
import com.upco.report.domain.responses.ResultRemote

interface RemoteDataSource {
    suspend fun login(request: LoginRequest): ResultRemote<LoginPayload>
    suspend fun register(request: RegisterRequest): ResultRemote<RegisterPayload>
    suspend fun getLogs(): ResultRemote<LogsPayload>
    suspend fun createLog(request: CreateLogRequest): ResultRemote<LogyPayload>
}