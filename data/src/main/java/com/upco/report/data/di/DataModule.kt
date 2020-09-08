package com.upco.report.data.di

import com.upco.report.data.LogsRepositoryImpl
import com.upco.report.data.UserRepositoryImpl
import com.upco.report.data.utils.TokenInterceptor
import com.upco.report.data.utils.TokenManager
import com.upco.report.domain.repository.LogsRepository
import com.upco.report.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> {
        UserRepositoryImpl(
            remoteDataSource = get(),
            tokenManager = get()
        )
    }
    factory<LogsRepository> {
        LogsRepositoryImpl(
            logsCacheDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val utilsModule = module {
    single {
        TokenManager(androidContext())
    }
    single {
        TokenInterceptor(tokenManager = get())
    }
}

val dataModules = listOf(
    dataCacheModule,
    dataRemoteModule,
    repositoryModule,
    utilsModule
)