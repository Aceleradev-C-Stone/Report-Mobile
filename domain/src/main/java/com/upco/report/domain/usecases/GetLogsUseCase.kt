package com.upco.report.domain.usecases

import com.upco.report.domain.entities.Log
import com.upco.report.domain.repository.LogsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler

class GetLogsUseCase(
    private val repository: LogsRepository,
    private val scheduler: Scheduler
) {

    fun execute(forceUpdate: Boolean): Observable<List<Log>> {
        return repository.getLogs(forceUpdate)
            .observeOn(scheduler)
    }
}