package com.upco.report.utils

import androidx.recyclerview.widget.DiffUtil
import com.upco.report.domain.entities.Log

class LogDiffCallback: DiffUtil.ItemCallback<Log>() {

    override fun areItemsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem == newItem
    }
}