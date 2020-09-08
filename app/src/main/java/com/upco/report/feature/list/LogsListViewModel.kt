package com.upco.report.feature.list

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upco.report.domain.entities.Log
import com.upco.report.domain.usecases.GetLogsUseCase
import com.upco.report.feature.common.BaseViewModel
import com.upco.report.feature.common.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LogsListViewModel(
    private val getLogsUseCase: GetLogsUseCase
): BaseViewModel() {

    private val _viewLogsState = MutableLiveData<Event<ViewLogsStates>>()
    val viewLogsState: LiveData<Event<ViewLogsStates>> = _viewLogsState

    fun getLogs() {
        viewModelScope.launch (Dispatchers.IO) {
            getLogsUseCase.execute().collect {
                val result = when (it) {
                    is GetLogsUseCase.ResultLogs.Logs -> ViewLogsStates.Show(it.list)
                    is GetLogsUseCase.ResultLogs.NoLogs -> ViewLogsStates.Empty
                    is GetLogsUseCase.ResultLogs.Error -> ViewLogsStates.Error
                }
                _viewLogsState.postValue(Event(result))
            }
        }
    }

    fun onTryAgainRequired() {
        getLogs()
    }

    sealed class ViewLogsStates {
        data class Show(val list: List<Log>): ViewLogsStates()
        object Empty: ViewLogsStates()
        object Error: ViewLogsStates()
    }
}