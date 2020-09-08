package com.upco.report.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.upco.report.databinding.ItemLogBinding
import com.upco.report.domain.entities.Log
import com.upco.report.utils.LogDiffCallback

class LogsAdapter(private val clickListener: LogClickListener):
    ListAdapter<Log, LogsAdapter.ViewHolder>(LogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLogBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemLogBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(log: Log) {
            binding.log = log
            binding.listener = clickListener
            binding.executePendingBindings()
        }
    }
}