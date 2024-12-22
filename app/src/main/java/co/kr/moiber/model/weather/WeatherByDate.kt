package co.kr.moiber.model.weather

import androidx.annotation.DrawableRes
import co.kr.moiber.R
import co.kr.moiber.shared.ext.getDayDiff
import co.kr.moiber.shared.ext.toKoreanWeekday
import java.util.Date

data class WeatherByDate(
    val date: Date? = null,
    val weather: Weather? = null,     // 날씨
    val minTemp: Int? = null,     // 최저온도
    val maxTemp: Int? = null,     // 최고온도
    val humidityIndex: Int? = null,   // 습도 지수
    val isDay: Boolean = true,        // 낮/밤 여부
) {

    @get:DrawableRes
    val weatherIconResId: Int
        get() = weather?.getWeatherIconResId(isDay) ?: R.drawable.icn_snow_l
}