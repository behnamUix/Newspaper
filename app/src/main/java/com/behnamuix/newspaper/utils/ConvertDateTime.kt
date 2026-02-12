package com.behnamuix.newspaper.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@RequiresApi(Build.VERSION_CODES.O)
fun convertDate(dateTime: String): String {
    // 1️⃣ تبدیل رشته ورودی به Instant
    val instant = Instant.parse(dateTime)

    // 2️⃣ تبدیل به زمان محلی تهران
    val tehranTime = instant.atZone(ZoneId.of("Asia/Tehran"))

    // 3️⃣ زمان فعلی تهران
    val nowTehran = ZonedDateTime.now(ZoneId.of("Asia/Tehran"))

    // 4️⃣ محاسبه اختلاف با Duration
    val duration = Duration.between(tehranTime, nowTehran)

    val days = duration.toDays()
    val hours = duration.toHours() - days * 24
    val minutes = duration.toMinutes() - duration.toHours() * 60
    val seconds = duration.seconds - duration.toMinutes() * 60

    // 5️⃣ خروجی مناسب
    return when {
        days > 0 -> {
            if (hours > 0) "$days days and $hours hours ago" else "$days days ago"
        }
        hours > 0 -> {
            if (minutes > 0) "$hours hours and $minutes minutes ago" else "$hours hours ago"
        }
        minutes > 0 -> "$minutes minutes ago"
        else -> "$seconds seconds ago"
    }
}
