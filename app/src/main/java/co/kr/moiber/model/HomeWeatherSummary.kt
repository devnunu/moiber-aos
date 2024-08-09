package co.kr.moiber.model

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
    val weatherInfoList: List<WeatherInfo> = listOf()
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
}

