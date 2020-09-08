package com.upco.report.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.upco.report.domain.entities.User
import com.upco.report.domain.usecases.LoginUseCase
import com.upco.report.feature.common.BaseViewModel
import com.upco.report.feature.common.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): BaseViewModel() {

    private val _loginState = MutableLiveData<Event<ViewLoginStates>>()
    val loginState: LiveData<Event<ViewLoginStates>> = _loginState

    private val inputEmail = MutableLiveData<String>().apply { postValue("") }
    private val inputPassword = MutableLiveData<String>().apply { postValue("") }

    fun login() {
        viewModelScope.launch (Dispatchers.IO) {
            loginUseCase.execute(inputEmail.value!!, inputPassword.value!!)
                .onStart {
                    val state = ViewLoginStates.Loading
                    _loginState.postValue(Event(state))
                }
                .collect {
                    val result = when (it) {
                        is LoginUseCase.ResultUser.Result -> ViewLoginStates.Success(it.user)
                        is LoginUseCase.ResultUser.Error -> ViewLoginStates.Error
                    }
                    _loginState.postValue(Event(result))
                }
        }
    }

    val setInputEmail = fun (email: String) {
        inputEmail.value = email
    }

    val setInputPassword = fun (password: String) {
        inputPassword.value = password
    }

    sealed class ViewLoginStates {
        data class Success(val user: User): ViewLoginStates()
        object Loading: ViewLoginStates()
        object Error: ViewLoginStates()
    }
}