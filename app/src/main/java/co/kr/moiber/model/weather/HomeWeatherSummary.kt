package co.kr.moiber.model.weather

import androidx.annotation.DrawableRes
import co.kr.moiber.R

data class HomeWeatherSummary(
    val currentWeather: Weather? = null,    // 날씨
    val currentTemp: Int? = null,           // 현재온도
    val maxTemp: Int? = null,               // 최고온도
    val minTemp: Int? = null,               // 최저온도
    val diffTemp: Int? = null,              // 어제 대비 온도
    val apparentTemp: Int? = null,          // 체감온도
    val dustIndex: Int? = null,       // 미세먼지 지수
    val humidityIndex: Int? = null,         // 습도 지수
    val windSpeedIndex: Int? = null,        // 풍속 지수
    val rainIndex: Int? = null,        // 강수확률
    val isDay: Boolean = true,        // 낮/밤 여부
    val weatherMessageList: List<WeatherMessage> = listOf()
) {

    val dustTxt: String
        get() = when (dustIndex) {
            in 0..30 -> "좋음"
            in 31..80 -> "보통"
            in 81..150 -> "나쁨"
            in 151..Int.MAX_VALUE -> "최악"
            else -> "-"
        }

    val humidityTxt: String
        get() = when (humidityIndex) {
            in 0..30 -> "건조"
            in 31..60 -> "쾌적"
            in 61..80 -> "꿉꿉"
            in 80..Int.MAX_VALUE -> "찐득"
            else -> "-"
        }

    val windSpeedTxt: String
        get() = when (windSpeedIndex) {
            0 -> "건조"
            1 -> "솔솔"
            in 2..3 -> "산들"
            in 4..5 -> "쌩쌩"
            6 -> "거센"
            in 7..8 -> "쌩쌩"
            else -> "-"
        }

    @get:DrawableRes
    val weatherIconResId: Int
        get() = when (currentWeather) {
            Weather.SUNNY -> {
                if (isDay) R.drawable.icn_sun_l else R.drawable.icn_moon_l
            }

            Weather.SOME_CLOUDY -> {
                if (isDay) R.drawable.icn_cloud1_l else R.drawable.icn_cloud3_l
            }

            Weather.CLOUDY -> R.drawable.icn_cloud2_l
            Weather.RAINY -> R.drawable.icn_rain_l
            Weather.THUNDER -> R.drawable.icn_thunder_l
            else -> R.drawable.icn_snow_l
        }

    @get:DrawableRes
    val weatherBgResId: Int
        get() = when (currentWeather) {
            Weather.SUNNY -> {
                if (isDay) {
                    val currentTemp = currentTemp ?: Int.MIN_VALUE
                    if (currentTemp > 30) R.drawable.back1_sun1 else R.drawable.back1_sun2
                } else {
                    R.drawable.back2_moon
                }
            }

            Weather.SOME_CLOUDY -> {
                if (isDay) R.drawable.back1_cloud1 else R.drawable.back2_cloud1
            }

            Weather.CLOUDY -> {
                if (isDay) R.drawable.back1_cloud2 else R.drawable.back2_cloud2
            }

            Weather.RAINY -> {
                if (isDay) R.drawable.back1_rain else R.drawable.back2_rain
            }

            Weather.THUNDER -> {
                if (isDay) R.drawable.back1_thunder else R.drawable.back2_thunder
            }

            else -> {
                if (isDay) R.drawable.back1_snow else R.drawable.back2_snow
            }
        }

    @get:DrawableRes
    val characterResId: Int
        get() {
            val currentTemp = this.currentTemp ?: 0
            return when (currentWeather) {
                Weather.SNOWY -> R.drawable.clothes_snow_a
                Weather.RAINY -> when {
                    currentTemp > 23 -> R.drawable.clothes_rain_c
                    currentTemp in -3..22 -> R.drawable.clothes_rain_b
                    else -> R.drawable.clothes_rain_a
                }
                else -> when {
                    currentTemp > 31 -> R.drawable.clothes_i
                    currentTemp in 27..31 -> R.drawable.clothes_h
                    currentTemp in 23..26 -> R.drawable.clothes_g
                    currentTemp in 17..22 -> R.drawable.clothes_f
                    currentTemp in 13..16 -> R.drawable.clothes_e
                    currentTemp in 6..12 -> R.drawable.clothes_d
                    currentTemp in -3..5 -> R.drawable.clothes_c
                    currentTemp in -16..-4 -> R.drawable.clothes_b
                    else -> R.drawable.clothes_a
                }
            }
        }

}

fun Int.isMoreThan(value: Int) = this > value

object FakeHomeWeatherSummary {
    fun getFakeModel() =
        HomeWeatherSummary(
            currentWeather = Weather.SUNNY,
            currentTemp = 32,
            maxTemp = 32,
            minTemp = 23,
            apparentTemp = 28,
            diffTemp = 3,
            dustIndex = 81,
            windSpeedIndex = 2,
            humidityIndex = 81,
            rainIndex = 60,
            weatherMessageList = listOf(
                WeatherMessage(
                    type = WeatherMessageType.CAUTION,
                    message = " 태풍경보 발령! 실내에 들어가 있어!"
                ),
                WeatherMessage(
                    type = WeatherMessageType.WARNING,
                    message = " 호우주의보 발령! 침수에 대비해!"
                ),
                WeatherMessage(
                    type = WeatherMessageType.NORMAL,
                    message = "오늘 기온이 정말 높은 하루야"
                )
            )
        )
}