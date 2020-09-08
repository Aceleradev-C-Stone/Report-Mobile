package com.upco.report.utils

import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import com.upco.report.extension.toLogChannel
import com.upco.report.extension.toLogLevel

@BindingAdapter("onTextChanged")
fun EditText.setOnTextChanged(action: (String) -> Unit) {
    doOnTextChanged { text, _, _, _ ->
        action.invoke(text.toString())
    }
}

@BindingAdapter("onLevelSelected")
fun AutoCompleteTextView.setOnLevelSelected(action: (LogLevel) -> Unit) {
    doOnTextChanged { text, _, _, _ ->
        action.invoke(text.toString().toLogLevel(context))
    }
}

@BindingAdapter("onChannelSelected")
fun AutoCompleteTextView.setOnChannelSelected(action: (LogChannel) -> Unit) {
    doOnTextChanged { text, _, _, _ ->
        action.invoke(text.toString().toLogChannel(context))
    }
}