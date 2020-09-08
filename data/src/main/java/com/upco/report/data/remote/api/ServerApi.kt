package com.upco.report.data.remote.api

import com.upco.report.data.remote.model.*
import retrofit2.http.*

interface ServerApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginPayload

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterPayload

    @GET("log")
    suspend fun fetchLogs(): LogsPayload

    @POST("log")
    suspend fun createLog(@Body request: CreateLogRequest): LogyPayload
}