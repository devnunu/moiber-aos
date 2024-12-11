package co.kr.moiber.model.weather

import androidx.annotation.DrawableRes
import co.kr.moiber.R

data class WeatherByDate(
    val weather: Weather? = null,     // 날씨
    val temperature: Int? = null,     // 현재온도
    val humidityIndex: Int? = null,   // 습도 지수
    val isDay: Boolean = true,        // 낮/밤 여부
) {

    @get:DrawableRes
    val weatherIconResId: Int
        get() = weather?.getWeatherIconResId(isDay) ?: R.drawable.icn_snow_l
}