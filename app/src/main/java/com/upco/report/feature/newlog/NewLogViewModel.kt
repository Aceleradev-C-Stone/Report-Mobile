package com.upco.report.feature.newlog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.upco.report.domain.entities.Log
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import com.upco.report.domain.usecases.CreateLogUseCase
import com.upco.report.feature.common.BaseViewModel
import com.upco.report.feature.common.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.*

class NewLogViewModel(
    private val createLogUseCase: CreateLogUseCase
): BaseViewModel() {

    private val _viewState = MutableLiveData<Event<ViewNewLogStates>>()
    val viewState: LiveData<Event<ViewNewLogStates>> = _viewState

    private val inputDescription = MutableLiveData<String>().apply { postValue("") }
    private val inputTitle = MutableLiveData<String>().apply { postValue("") }
    private val inputDetails = MutableLiveData<String>().apply { postValue("") }
    private val inputSource = MutableLiveData<String>().apply { postValue("") }
    private val inputEventCount = MutableLiveData<String>().apply { postValue("") }
    private val inputLevel = MutableLiveData<LogLevel>()
    private val inputChannel = MutableLiveData<LogChannel>()

    fun save() {
        viewModelScope.launch (Dispatchers.IO) {
            createLogUseCase.execute(mapInputToLog())
                .onStart {
                    val state = ViewNewLogStates.Loading
                    _viewState.postValue(Event(state))
                }
                .collect {
                    val result = when (it) {
                        is CreateLogUseCase.ResultLog.Result -> ViewNewLogStates.Success
                        is CreateLogUseCase.ResultLog.Error -> ViewNewLogStates.Error
                    }
                    _viewState.postValue(Event(result))
                }
        }
    }

    private fun mapInputToLog(): Log {
        return Log(
            0,
            inputDescription.value!!,
            inputTitle.value!!,
            inputDetails.value!!,
            inputSource.value!!,
            inputEventCount.value!!.toInt(),
            inputLevel.value!!,
            inputChannel.value!!,
            Date(),
            false,
            0, ""
        )
    }

    val setInputDescription = fun (description: String) {
        inputDescription.value = description
    }

    val setInputTitle = fun (title: String) {
        inputTitle.value = title
    }

    val setInputDetails = fun (details: String) {
        inputDetails.value = details
    }

    val setInputSource = fun (source: String) {
        inputSource.value = source
    }

    val setInputEventCount = fun (eventCount: String) {
        inputEventCount.value = eventCount
    }

    val setInputLevel = fun (level: LogLevel) {
        inputLevel.value = level
    }

    val setInputChannel = fun (channel: LogChannel) {
        inputChannel.value = channel
    }

    sealed class ViewNewLogStates {
        object Success: ViewNewLogStates()
        object Loading: ViewNewLogStates()
        object Error: ViewNewLogStates()
    }
}