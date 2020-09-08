package com.upco.report.extension

import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(
    message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}