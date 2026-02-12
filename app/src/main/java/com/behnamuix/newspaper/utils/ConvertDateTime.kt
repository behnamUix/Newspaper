package com.behnamuix.newspaper.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
@RequiresApi(Build.VERSION_CODES.O)
fun convertDate(dateTime: String): String {
   val instant = Instant.parse(dateTime)
   val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()

   val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
   return date.format(formatter)   // 09 Feb 2026
}