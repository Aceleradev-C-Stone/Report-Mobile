package com.upco.report.extension

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

inline fun View.snackbar(
    @StringRes messageRes: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    func: Snackbar.() -> Unit = {}) {
    snackbar(resources.getString(messageRes), duration, func)
}

inline fun View.snackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    func: Snackbar.() -> Unit = {}) {
    val snackbar = Snackbar.make(this, message, duration)
    snackbar.func()
    snackbar.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}