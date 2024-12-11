package co.kr.moiber.model.weather

import co.kr.moiber.R
import co.kr.moiber.model.weather.Weather.CLOUDY
import co.kr.moiber.model.weather.Weather.RAINY
import co.kr.moiber.model.weather.Weather.SOME_CLOUDY
import co.kr.moiber.model.weather.Weather.SUNNY
import co.kr.moiber.model.weather.Weather.THUNDER


enum class Weather {
    SUNNY,      // 맑음
    SOME_CLOUDY,   // 구름 조금
    CLOUDY,         // 구름 많음
    RAINY,          // 비
    THUNDER,        // 번개
    SNOWY;           // 눈
}

fun Weather?.getWeatherIconResId(isDay: Boolean): Int =
    when (this) {
        SUNNY -> {
            if (isDay) R.drawable.icn_sun_l else R.drawable.icn_moon_l
        }

        SOME_CLOUDY -> {
            if (isDay) R.drawable.icn_cloud1_l else R.drawable.icn_cloud3_l
        }

        CLOUDY -> R.drawable.icn_cloud2_l
        RAINY -> R.drawable.icn_rain_l
        THUNDER -> R.drawable.icn_thunder_l
        else -> R.drawable.icn_snow_l
    }