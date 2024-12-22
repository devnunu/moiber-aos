package co.kr.moiber.shared.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

fun Date?.toFormatString(
    format: String,
): String? {
    if (this == null) return null
    val sdf = SimpleDateFormat(format, Locale.KOREA)
    sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
    return sdf.format(this)
}

/**
 * Manipulate Date
 * */
// Date에 1년 더하기
fun Date.addYear(year: Int): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.YEAR, year)
    return cal.time
}

// Date에 1달 더하기
fun Date.addMonth(month: Int): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.MONTH, month)
    return cal.time
}

// Date에 1주일 더하기
fun Date.addWeek(week: Int): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.WEEK_OF_YEAR, week)
    return cal.time
}

// Date에 1일 더하기
fun Date.addDay(day: Int): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.DATE, day)
    return cal.time
}

// Date에 1일 더하기
fun Date.addHour(hour: Int): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.HOUR, hour)
    return cal.time
}

/**
 * Diff
 * */
fun Date.getDateForMidnight(): Date =
    this.getCalendarForMidnight().time

private fun Date.getCalendarForMidnight(): Calendar {
    val cal = Calendar.getInstance()
    cal.time = this
    cal[Calendar.HOUR_OF_DAY] = 0 // set hour to midnight
    cal[Calendar.MINUTE] = 0 // set minute in hour
    cal[Calendar.SECOND] = 0 // set second in minute
    cal[Calendar.MILLISECOND] = 0 // set millisecond in second
    return cal
}

fun Date.getHourDiff(postDate: Date): Long {
    val diffInMillis = this.time - postDate.time
    return TimeUnit.MILLISECONDS.toHours(diffInMillis)
}

fun Date.getDayDiff(postDate: Date): Long {
    val prevTime = this.getDateForMidnight()
    val postTime = postDate.getDateForMidnight()
    return (postTime.time - prevTime.time) / 1000 / (60 * 60 * 24)
}

fun Date.getWeekDiff(postDate: Date): Long {
    val diffInMillis = this.time - postDate.time
    return TimeUnit.MILLISECONDS.toDays(diffInMillis) / 7
}

fun Date.getMonthDiff(postDate: Date): Int {
    val prevTime = this.getCalendarForMidnight()
    val postTime = postDate.getCalendarForMidnight()
    val prevMonth = prevTime[Calendar.YEAR] * 12 + prevTime[Calendar.MONTH]
    val postMonth = postTime[Calendar.YEAR] * 12 + postTime[Calendar.MONTH]
    return postMonth - prevMonth
}

fun Date.getYearDiff(postDate: Date): Int =
    this.getMonthDiff(postDate) / 12


/** getter */
fun Date.getCurrentHour(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun Date.toKoreanWeekday(): String {
    val calendar = Calendar.getInstance().apply {
        time = this@toKoreanWeekday
    }

    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.SUNDAY -> "일"
        Calendar.MONDAY -> "월"
        Calendar.TUESDAY -> "화"
        Calendar.WEDNESDAY -> "수"
        Calendar.THURSDAY -> "목"
        Calendar.FRIDAY -> "금"
        Calendar.SATURDAY -> "토"
        else -> throw IllegalArgumentException("잘못된 요일입니다.")
    }
}

