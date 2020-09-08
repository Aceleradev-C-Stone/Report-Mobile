package com.upco.report.di

import com.upco.report.feature.common.BaseViewModel
import com.upco.report.feature.list.LogsAdapter
import com.upco.report.feature.list.LogsListViewModel
import com.upco.report.feature.login.LoginViewModel
import com.upco.report.feature.newlog.NewLogViewModel
import com.upco.report.feature.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { BaseViewModel() }
    viewModel {
        LoginViewModel(
            loginUseCase = get()
        )
    }
    viewModel {
        RegisterViewModel(
            registerUseCase = get()
        )
    }
    viewModel {
        LogsListViewModel(
            getLogsUseCase = get()
        )
    }
    viewModel {
        NewLogViewModel(
            createLogUseCase = get()
        )
    }
}