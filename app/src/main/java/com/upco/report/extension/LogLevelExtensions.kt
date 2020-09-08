package com.upco.report.extension

import android.content.Context
import androidx.core.content.ContextCompat
import com.upco.report.R
import com.upco.report.domain.enums.LogLevel

fun LogLevel.getName(context: Context): String {
    val stringId = when (this) {
        LogLevel.DEBUG -> R.string.debug
        LogLevel.WARNING -> R.string.warning
        LogLevel.ERROR -> R.string.error
    }
    return context.getString(stringId)
}

fun LogLevel.getIndicatorColor(context: Context): Int {
    val colorId = when (this) {
        LogLevel.DEBUG -> R.color.colorIndicatorDebug
        LogLevel.WARNING -> R.color.colorIndicatorWarning
        LogLevel.ERROR -> R.color.colorIndicatorError
    }
    return ContextCompat.getColor(context, colorId)
}

fun String.toLogLevel(context: Context): LogLevel {
    return when (this) {
        context.getString(R.string.debug) -> LogLevel.DEBUG
        context.getString(R.string.warning) -> LogLevel.WARNING
        context.getString(R.string.error) -> LogLevel.ERROR
        else -> LogLevel.DEBUG
    }
}