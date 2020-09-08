package com.upco.report.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy  HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}