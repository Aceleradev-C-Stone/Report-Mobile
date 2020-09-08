package com.upco.report.data.remote.mapper

import com.upco.report.data.remote.model.CreateLogRequest
import com.upco.report.data.remote.model.LogPayload
import com.upco.report.data.remote.model.LogsPayload
import com.upco.report.domain.entities.Log

object LogMapper {

    fun map(payload: LogsPayload) = payload.logsPayload.map { map(it) }

    fun map(payload: LogPayload) = Log(
        id = payload.id,
        description = payload.description,
        title = payload.title,
        details = payload.details,
        source = payload.source,
        eventCount = payload.eventCount,
        level = payload.level,
        channel = payload.channel,
        createdAt = payload.createdAt,
        archived = payload.archived,
        userId = payload.userId,
        userName = payload.userName
    )

    fun mapToRequest(log: Log) = CreateLogRequest(
        description = log.description,
        title = log.title,
        details = log.details,
        source = log.source,
        eventCount = log.eventCount,
        level = log.level,
        channel = log.channel
    )
}