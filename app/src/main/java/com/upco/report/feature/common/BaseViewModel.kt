package com.upco.report.feature.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

open class BaseViewModel: ViewModel() {

    private val _newDestination = MutableLiveData<Event<NavDirections>>()
    val newDestination: LiveData<Event<NavDirections>> = _newDestination

    fun setNewDestination(destination: NavDirections)
            = _newDestination.postValue(Event(destination))
}