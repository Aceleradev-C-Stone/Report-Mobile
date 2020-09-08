package com.upco.report.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.upco.report.R
import com.upco.report.databinding.FragmentLogsListBinding
import com.upco.report.domain.entities.Log
import com.upco.report.extension.hide
import com.upco.report.extension.show
import com.upco.report.feature.common.BaseFragment
import com.upco.report.utils.exhaustive

class LogsListFragment: BaseFragment<LogsListViewModel>(LogsListViewModel::class) {

    private lateinit var binding: FragmentLogsListBinding
    private lateinit var logsAdapter: LogsAdapter
    private lateinit var footerAdapter: FooterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_logs_list, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()
        setupBinding()
        setupAdapter()
        setupRecyclerView()
        setupOnBackPressedCallback()
    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.getLogs()
        viewModel.viewLogsState.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { state ->
                hideAll()
                when (state) {
                    is LogsListViewModel.ViewLogsStates.Show -> showContent(state.list)
                    is LogsListViewModel.ViewLogsStates.Empty -> showEmptyState()
                    is LogsListViewModel.ViewLogsStates.Error -> showErrorState()
                }.exhaustive
            }
        })
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupAdapter() {
        logsAdapter = LogsAdapter(LogClickListener { log ->
            val destination = LogsListFragmentDirections.toLogDetailsFragment(log)
            viewModel.setNewDestination(destination)
        })
        footerAdapter = FooterAdapter()
    }

    private fun setupRecyclerView() = with (binding.recyclerView) {
        adapter = ConcatAdapter(logsAdapter, footerAdapter)
        layoutManager = LinearLayoutManager(activity)
    }

    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    private fun showContent(list: List<Log>) = with (logsAdapter) {
        submitList(list)
        binding.recyclerView.show()
    }

    private fun showEmptyState() {
        binding.txtEmpty.show()
    }

    private fun showErrorState() {
        binding.btnTryAgain.show()
    }

    private fun hideAll() {
        binding.progressBar.hide()
        binding.recyclerView.hide()
        binding.btnTryAgain.hide()
        binding.txtEmpty.hide()
    }
}