package com.upco.report.data.local.source

import com.upco.report.data.local.database.LogsDao
import com.upco.report.data.local.mapper.LogCacheMapper
import com.upco.report.data.local.model.LogCache
import com.upco.report.domain.entities.Log
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class LogsCacheDataSourceImpl(
    private val logsDao: LogsDao
): LogsCacheDataSource {

    override fun getLogs() = logsDao.getLogs()

    override fun insertData(logCache: LogCache) {
        logsDao.insert(logCache)
    }

    override fun updateData(cacheList: List<LogCache>) {
        logsDao.updateData(cacheList)
    }
}