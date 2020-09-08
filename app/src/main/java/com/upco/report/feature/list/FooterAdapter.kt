package com.upco.report.feature.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upco.report.R
import com.upco.report.extension.inflate

class FooterAdapter: RecyclerView.Adapter<FooterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_footer, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount() = 1

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}