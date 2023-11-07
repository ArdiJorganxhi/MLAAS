package dev.ardijorganxhi.mlaas.utils

import java.util.Date
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

object DateUtils {

    fun convertLocalDateTimeToDate(localDateTime: LocalDateTime): Date {
        val zoneId = ZoneId.of("America/New_York")
        val zonedDateTime = localDateTime.atZone(zoneId)
        return Date.from(zonedDateTime.toInstant())
    }
}
