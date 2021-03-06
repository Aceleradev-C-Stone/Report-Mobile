package com.upco.report.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import java.util.*

@Entity(tableName = "logs")
data class LogCache(
    @PrimaryKey(autoGenerate = false)
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
)