package com.upco.report.data.remote.model

import com.google.gson.annotations.SerializedName
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel

data class CreateLogRequest(
    @SerializedName("description") val description: String,
    @SerializedName("title") val title: String,
    @SerializedName("details") val details: String,
    @SerializedName("source") val source: String,
    @SerializedName("eventCount") val eventCount: Int,
    @SerializedName("level") val level: LogLevel,
    @SerializedName("channel") val channel: LogChannel
)