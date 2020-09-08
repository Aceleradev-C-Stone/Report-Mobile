package com.upco.report

import android.app.Application
import com.upco.report.data.di.dataModules
import com.upco.report.di.presentationModule
import com.upco.report.domain.di.domainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ReportApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ReportApplication)
            modules(domainModules + dataModules + presentationModule)
        }
    }
}