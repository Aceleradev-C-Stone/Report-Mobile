package com.upco.report.domain.entities

import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import java.io.Serializable
import java.util.*

data class Log(
    val id: Int,
    val description: String,
    val title: String,
    val details: String,
    val source: String,
    val eventCount: Int,
    val level: LogLevel,
    val channel: LogChannel,
    val createdAt: Date,
    val archived: Boolean,
    val userId: Int,
    val userName: String
): Serializable