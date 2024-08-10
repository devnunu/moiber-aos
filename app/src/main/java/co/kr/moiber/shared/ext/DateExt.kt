package co.kr.moiber.shared.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Date?.toFormatString(
    format: String,
): String? {
    if (this == null) return null
    val sdf = SimpleDateFormat(format, Locale.KOREA)
    sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
    return sdf.format(this)
}
