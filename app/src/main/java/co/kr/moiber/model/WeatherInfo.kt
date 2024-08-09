package co.kr.moiber.model

data class WeatherInfo(
    val type: WeatherInfoType? = null,
    val text: String? = null
)

enum class WeatherInfoType {
    NORMAL,     // 일반
    CAUTION,    // 주의
    WARNING     // 경고
}