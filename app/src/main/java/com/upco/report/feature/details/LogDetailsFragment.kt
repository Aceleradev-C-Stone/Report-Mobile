package com.upco.report.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.upco.report.R
import com.upco.report.databinding.FragmentLogDetailsBinding
import com.upco.report.domain.entities.Log
import com.upco.report.extension.getName
import com.upco.report.extension.toFormattedString
import com.upco.report.feature.common.BaseFragment
import com.upco.report.feature.common.BaseViewModel
import kotlinx.android.synthetic.main.appbar.*

class LogDetailsFragment: BaseFragment<BaseViewModel>(BaseViewModel::class) {

    private lateinit var binding: FragmentLogDetailsBinding
    private val navArgs: LogDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_log_details, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()
        setupBinding()
        setupToolbar()
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.log = navArgs.log
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupToolbar()  {
        val toolbar = requireActivity().toolbar
        toolbar.title = getToolbarTitle()
    }

    private fun getToolbarTitle(): String = with (navArgs.log) {
        val level = level.getName(requireContext())
        val createdAt = createdAt.toFormattedString()
        return "$level no $source em $createdAt"
    }
}