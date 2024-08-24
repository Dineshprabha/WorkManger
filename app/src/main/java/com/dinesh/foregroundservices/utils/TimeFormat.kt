package com.dinesh.foregroundservices.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTimestamptoDMY(timestamp : Long) : String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val date = Date(timestamp)
    return sdf.format(date)
}