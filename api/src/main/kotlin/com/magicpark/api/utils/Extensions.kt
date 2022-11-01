package com.magicpark.api.utils

import java.sql.Timestamp
import java.util.*

fun getCurrentDate() : Date = Date().apply {
    time = System.currentTimeMillis()
}

fun getCurrentTimestamp() : Timestamp = getCurrentTimestamp().time as Timestamp

fun Timestamp.isExpired(): Boolean = getCurrentDate().after(this)
fun Timestamp.isAvailable(): Boolean = getCurrentDate().before(this)

fun createTimestamp(minutes: Int) : Timestamp {
    val expiresAt = Calendar.getInstance()
    expiresAt.add(Calendar.MINUTE, minutes)

    return expiresAt.time as Timestamp
}