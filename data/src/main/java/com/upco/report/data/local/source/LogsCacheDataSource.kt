package com.upco.report.data.local.source

import com.upco.report.data.local.model.LogCache
import kotlinx.coroutines.flow.Flow

interface LogsCacheDataSource {
    fun getLogs(): Flow<List<LogCache>>
    fun insertData(logCache: LogCache)
    fun updateData(cacheList: List<LogCache>)
}