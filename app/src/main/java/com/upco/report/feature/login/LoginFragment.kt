package com.upco.report.feature.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.upco.report.R
import com.upco.report.databinding.FragmentLoginBinding
import com.upco.report.domain.entities.User
import com.upco.report.extension.hide
import com.upco.report.extension.show
import com.upco.report.extension.snackbar
import com.upco.report.extension.toast
import com.upco.report.feature.common.BaseFragment
import com.upco.report.feature.common.BaseViewModel
import com.upco.report.feature.list.LogsListViewModel
import com.upco.report.utils.exhaustive

class LoginFragment: BaseFragment<LoginViewModel>(LoginViewModel::class) {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()
        setupBinding()
    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.loginState.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { state ->
                when (state) {
                    is LoginViewModel.ViewLoginStates.Success -> showContent(state.user)
                    is LoginViewModel.ViewLoginStates.Loading -> showLoadingState()
                    is LoginViewModel.ViewLoginStates.Error -> showErrorState()
                }.exhaustive
            }
        })
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.toLogsList = LoginFragmentDirections.toLogsListFragment()
        binding.toRegister = LoginFragmentDirections.toRegisterFragment()
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun showContent(user: User) {
        hideProgressBar()
        enableFields()
        viewModel.setNewDestination(
            LoginFragmentDirections.toLogsListFragment())
    }

    private fun showErrorState() {
        hideProgressBar()
        enableFields()
        binding.loginButton.snackbar(
            "Erro! Tente novamente ;)", Snackbar.LENGTH_LONG)
    }

    private fun showLoadingState() {
        showProgressBar()
        disableFields()
    }

    private fun showProgressBar() = binding.progressBarLayout.show()
    private fun hideProgressBar() = binding.progressBarLayout.hide()

    private fun enableFields() = with(binding) {
        emailInput.isEnabled = true
        passwordInput.isEnabled = true
        loginButton.isEnabled = true
        registerButton.isEnabled = true
    }

    private fun disableFields() = with(binding) {
        emailInput.isEnabled = false
        passwordInput.isEnabled = false
        loginButton.isEnabled = false
        registerButton.isEnabled = false
    }
}