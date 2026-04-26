package com.example.webcomposetest.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@OptIn(FormatStringsInDatetimeFormats::class)
fun Long.toFormattedDateTimeString(format: String): String {
    val systemTimeZone = TimeZone.currentSystemDefault()
    val systemDateTime = Instant.fromEpochMilliseconds(this).toLocalDateTime(systemTimeZone)
    val dateTimeFormat = LocalDateTime.Format { byUnicodePattern(format) }

    return systemDateTime.format(dateTimeFormat)
}