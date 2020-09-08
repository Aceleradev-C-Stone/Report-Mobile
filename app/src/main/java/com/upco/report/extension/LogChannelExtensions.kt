package com.upco.report.extension

import android.content.Context
import com.upco.report.R
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel

fun LogChannel.getName(context: Context): String {
    val stringId = when (this) {
        LogChannel.DEVELOPMENT -> R.string.development
        LogChannel.PRODUCTION -> R.string.production
    }
    return context.getString(stringId)
}

fun String.toLogChannel(context: Context): LogChannel {
    return when (this) {
        context.getString(R.string.production) -> LogChannel.PRODUCTION
        context.getString(R.string.development) -> LogChannel.DEVELOPMENT
        else -> LogChannel.DEVELOPMENT
    }
}