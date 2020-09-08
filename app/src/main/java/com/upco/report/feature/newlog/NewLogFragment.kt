package com.upco.report.feature.newlog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.upco.report.R
import com.upco.report.databinding.FragmentNewLogBinding
import com.upco.report.extension.hide
import com.upco.report.extension.show
import com.upco.report.extension.snackbar
import com.upco.report.feature.common.BaseFragment
import com.upco.report.utils.exhaustive

class NewLogFragment: BaseFragment<NewLogViewModel>(NewLogViewModel::class) {

    private lateinit var binding: FragmentNewLogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_log, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()
        setupBinding()
        setupLevelInput()
        setupChannelInput()
    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.viewState.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { state ->
                when (state) {
                    is NewLogViewModel.ViewNewLogStates.Success -> showContent()
                    is NewLogViewModel.ViewNewLogStates.Loading -> showLoadingState()
                    is NewLogViewModel.ViewNewLogStates.Error -> showErrorState()
                }.exhaustive
            }
        })
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupLevelInput() = with (binding.levelInput) {
        val levels = arrayOf(
            R.string.debug,
            R.string.warning,
            R.string.error
        ).map { getString(it) }

        val levelsAdapter = ArrayAdapter(context, R.layout.item_spinner, levels)
        setAdapter(levelsAdapter)
        setText(levels[0], false)
    }

    private fun setupChannelInput() = with (binding.channelInput) {
        val channels = arrayOf(
            R.string.development,
            R.string.production
        ).map { getString(it) }

        val channelsAdapter = ArrayAdapter(context, R.layout.item_spinner, channels)
        setAdapter(channelsAdapter)
        setText(channels[0], false)
    }

    private fun showContent() {
        hideProgressBar()
        enableFields()
        // Call onBack
    }

    private fun showErrorState() {
        hideProgressBar()
        enableFields()
        binding.saveButton.snackbar(
            "Erro! Tente novamente ;)", Snackbar.LENGTH_LONG)
    }

    private fun showLoadingState() {
        showProgressBar()
        disableFields()
    }

    private fun showProgressBar() = binding.progressBarLayout.show()
    private fun hideProgressBar() = binding.progressBarLayout.hide()

    private fun enableFields() = with(binding) {
        descriptionInput.isEnabled = true
        titleInput.isEnabled = true
        detailsInput.isEnabled = true
        sourceInput.isEnabled = true
        eventCountInput.isEnabled = true
        levelInput.isEnabled = true
        channelInput.isEnabled = true
        saveButton.isEnabled = true
    }

    private fun disableFields() = with(binding) {
        descriptionInput.isEnabled = false
        titleInput.isEnabled = false
        detailsInput.isEnabled = false
        sourceInput.isEnabled = false
        eventCountInput.isEnabled = false
        levelInput.isEnabled = false
        channelInput.isEnabled = false
        saveButton.isEnabled = false
    }
}