package com.upco.report.domain.di

import com.upco.report.domain.usecases.GetLogsUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetLogsUseCase(
            repository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModules = listOf(useCaseModule)