package com.upco.report.data.local.mapper

import com.upco.report.data.local.model.LogCache
import com.upco.report.domain.entities.Log
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import java.util.*

object LogCacheMapper {

    fun map(logs: List<LogCache>) = logs.map { map(it) }

    private fun map(log: LogCache) = Log(
        id = log.id,
        description = log.description,
        title = log.title,
        details = log.details,
        source = log.source,
        eventCount = log.eventCount,
        level = log.level,
        channel = log.channel,
        createdAt = log.createdAt,
        archived = log.archived,
        userId = log.userId,
        userName = log.userName
    )

    fun mapLogsToCache(logs: List<Log>) = logs.map { map(it) }

    fun map(log: Log) = LogCache(
        id = log.id,
        description = log.description,
        title = log.title,
        details = log.details,
        source = log.source,
        eventCount = log.eventCount,
        level = log.level,
        channel = log.channel,
        createdAt = log.createdAt,
        archived = log.archived,
        userId = log.userId,
        userName = log.userName
    )
}