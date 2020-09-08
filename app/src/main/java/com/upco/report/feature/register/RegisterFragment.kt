package com.upco.report.feature.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.upco.report.R
import com.upco.report.databinding.FragmentRegisterBinding
import com.upco.report.domain.entities.User
import com.upco.report.extension.hide
import com.upco.report.extension.show
import com.upco.report.extension.snackbar
import com.upco.report.feature.common.BaseFragment
import com.upco.report.feature.common.BaseViewModel
import com.upco.report.feature.login.LoginFragmentDirections
import com.upco.report.feature.login.LoginViewModel
import com.upco.report.utils.exhaustive

class RegisterFragment: BaseFragment<RegisterViewModel>(RegisterViewModel::class) {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()
        setupBinding()
    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.registerState.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { state ->
                when (state) {
                    is RegisterViewModel.ViewRegisterStates.Success -> showContent()
                    is RegisterViewModel.ViewRegisterStates.Loading -> showLoadingState()
                    is RegisterViewModel.ViewRegisterStates.Error -> showErrorState()
                }.exhaustive
            }
        })
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.toLogin = RegisterFragmentDirections.toLoginFragment()
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun showContent() {
        hideProgressBar()
        enableFields()
        viewModel.setNewDestination(
            RegisterFragmentDirections.toLoginFragment())
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
        nameInput.isEnabled = true
        emailInput.isEnabled = true
        passwordInput.isEnabled = true
        registerButton.isEnabled = true
        loginButton.isEnabled = true
    }

    private fun disableFields() = with(binding) {
        nameInput.isEnabled = false
        emailInput.isEnabled = false
        passwordInput.isEnabled = false
        registerButton.isEnabled = false
        loginButton.isEnabled = false
    }
}