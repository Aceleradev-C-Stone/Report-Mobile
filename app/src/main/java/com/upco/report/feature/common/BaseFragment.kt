package com.upco.report.feature.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<T: BaseViewModel>(viewModelClass: KClass<T>): Fragment() {

    protected val viewModel: T by viewModel(viewModelClass)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupViewModel()
    }

    protected open fun setupView() {}

    protected open fun setupViewModel() {
        viewModel.newDestination.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { destination ->
                navigate(destination)
            }
        })
    }

    private fun navigate(destination: NavDirections) {
        findNavController().navigate(destination)
    }
}