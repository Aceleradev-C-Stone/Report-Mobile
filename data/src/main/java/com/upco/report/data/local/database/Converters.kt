package com.upco.report.data.local.database

import androidx.room.TypeConverter
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import java.util.*

class Converters {

    companion object {

        @JvmStatic
        @TypeConverter
        fun timestampToDate(timestamp: Long) = Date(timestamp)

        @JvmStatic
        @TypeConverter
        fun dateToTimestamp(date: Date) = date.time

        @JvmStatic
        @TypeConverter
        fun indexToLogLevel(index: Int) = LogLevel.values()[index]

        @JvmStatic
        @TypeConverter
        fun logLevelToIndex(level: LogLevel) = level.ordinal

        @JvmStatic
        @TypeConverter
        fun indexToLogChannel(index: Int) = LogChannel.values()[index]

        @JvmStatic
        @TypeConverter
        fun logChannelToIndex(channel: LogChannel) = channel.ordinal
    }
}