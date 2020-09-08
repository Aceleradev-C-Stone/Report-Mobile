package com.upco.report.data.remote.source

import com.upco.report.data.extensions.mapRemoteErrors
import com.upco.report.data.remote.api.ServerApi
import com.upco.report.data.remote.mapper.LogMapper
import com.upco.report.data.remote.model.*
import com.upco.report.domain.entities.Log
import com.upco.report.domain.responses.ResultRemote
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val serverApi: ServerApi
): RemoteDataSource {

    override suspend fun login(request: LoginRequest): ResultRemote<LoginPayload> {
        return try {
            val payload = serverApi.login(request)
            ResultRemote.Success(response = payload)
        } catch (throwable: Throwable) {
            throwable.mapRemoteErrors()
        }
    }

    override suspend fun register(request: RegisterRequest): ResultRemote<RegisterPayload> {
        return try {
            val payload = serverApi.register(request)
            ResultRemote.Success(response = payload)
        } catch (throwable: Throwable) {
            throwable.mapRemoteErrors()
        }
    }

    override suspend fun getLogs(): ResultRemote<LogsPayload> {
        return try {
            val payload = serverApi.fetchLogs()
            ResultRemote.Success(response = payload)
        } catch (throwable: Throwable) {
            throwable.mapRemoteErrors()
        }
    }

    override suspend fun createLog(request: CreateLogRequest): ResultRemote<LogyPayload> {
        return try {
            val payload = serverApi.createLog(request)
            ResultRemote.Success(response = payload)
        } catch (throwable: Throwable) {
            throwable.mapRemoteErrors()
        }
    }
}