package com.upco.report.domain.di

import com.upco.report.domain.usecases.*
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {
    factory<LoginUseCase> {
        LoginUseCaseImpl(
            repository = get()
        )
    }
    factory<RegisterUseCase> {
        RegisterUseCaseImpl(
            repository = get()
        )
    }
    factory<GetLogsUseCase> {
        GetLogsUseCaseImpl(
            repository = get()
        )
    }
    factory<CreateLogUseCase> {
        CreateLogUseCaseImpl(
            repository = get()
        )
    }
}

val domainModules = listOf(useCaseModule)