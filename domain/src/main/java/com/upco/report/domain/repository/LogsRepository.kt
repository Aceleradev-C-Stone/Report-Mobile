package com.upco.report.domain.repository

import com.upco.report.domain.entities.Log
import io.reactivex.rxjava3.core.Observable

interface LogsRepository {
    fun getLogs(forceUpdate: Boolean): Observable<List<Log>>
}