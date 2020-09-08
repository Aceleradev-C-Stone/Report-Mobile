package com.upco.report.data.di

import com.upco.report.data.local.database.LogsDatabase
import com.upco.report.data.local.source.LogsCacheDataSource
import com.upco.report.data.local.source.LogsCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataCacheModule = module {
    single { LogsDatabase.createDatabase(androidContext()) }
    factory<LogsCacheDataSource> { LogsCacheDataSourceImpl(logsDao = get()) }
}