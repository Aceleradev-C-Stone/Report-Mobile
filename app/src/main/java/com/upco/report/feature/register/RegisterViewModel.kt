package com.upco.report.feature.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.upco.report.domain.usecases.RegisterUseCase
import com.upco.report.feature.common.BaseViewModel
import com.upco.report.feature.common.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): BaseViewModel() {

    private val _registerState = MutableLiveData<Event<ViewRegisterStates>>()
    val registerState: LiveData<Event<ViewRegisterStates>> = _registerState

    private val inputName = MutableLiveData<String>().apply { postValue("") }
    private val inputEmail = MutableLiveData<String>().apply { postValue("") }
    private val inputPassword = MutableLiveData<String>().apply { postValue("") }

    fun register() {
        viewModelScope.launch (Dispatchers.IO) {
            registerUseCase.execute(inputName.value!!, inputEmail.value!!, inputPassword.value!!)
                .onStart {
                    val state = ViewRegisterStates.Loading
                    _registerState.postValue(Event(state))
                }
                .collect {
                    val result = when (it) {
                        is RegisterUseCase.ResultUser.Result -> ViewRegisterStates.Success
                        is RegisterUseCase.ResultUser.Error -> ViewRegisterStates.Error
                    }
                    _registerState.postValue(Event(result))
                }
        }
    }

    val setInputName = fun (name: String) {
        inputName.value = name
    }

    val setInputEmail = fun (email: String) {
        inputEmail.value = email
    }

    val setInputPassword = fun (password: String) {
        inputPassword.value = password
    }

    sealed class ViewRegisterStates {
        object Success: ViewRegisterStates()
        object Loading: ViewRegisterStates()
        object Error: ViewRegisterStates()
    }
}