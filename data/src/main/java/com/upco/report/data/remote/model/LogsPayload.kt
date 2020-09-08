package com.upco.report.data.remote.model

import com.google.gson.annotations.SerializedName
import com.upco.report.domain.entities.Log
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import java.util.*

class LogsPayload(
    @SerializedName("data") val logsPayload: List<LogPayload>
)

class LogyPayload(
    @SerializedName("data") val log: LogPayload
)

class LogPayload(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("title") val title: String,
    @SerializedName("details") val details: String,
    @SerializedName("source") val source: String,
    @SerializedName("eventCount") val eventCount: Int,
    @SerializedName("level") val level: LogLevel,
    @SerializedName("channel") val channel: LogChannel,
    @SerializedName("createdAt") val createdAt: Date,
    @SerializedName("archived") val archived: Boolean,
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String
)