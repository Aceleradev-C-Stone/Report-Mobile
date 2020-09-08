package com.upco.report.feature.list

import com.upco.report.domain.entities.Log

interface LogClickListener {
    fun onClick(log: Log)
}

fun LogClickListener(clickListener: (log: Log) -> Unit): LogClickListener {
    return object : LogClickListener {
        override fun onClick(log: Log) = clickListener(log)
    }
}