package com.cristiansofthouse.testhistory.utils

import java.text.SimpleDateFormat
import java.util.Date

fun Long.formatDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}
