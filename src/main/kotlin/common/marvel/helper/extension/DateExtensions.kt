package common.marvel.helper.extension

import java.time.LocalDateTime

fun LocalDateTime.isBetween(start: LocalDateTime, end: LocalDateTime): Boolean =
    (this.isEqual(start) || this.isAfter(start)) && (this.isBefore(end) || this.isEqual(end))
