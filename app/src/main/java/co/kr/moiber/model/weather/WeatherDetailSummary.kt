package co.kr.moiber.model.weather

import co.kr.moiber.shared.ext.addDay
import co.kr.moiber.shared.ext.addHour
import java.util.Date

data class WeatherDetailSummary(
    val weatherByHourList: List<WeatherByHour> = emptyList(),
    val weatherByDateList: List<WeatherByDate> = emptyList()
)

object FakeWeatherDetail {
    fun getFakeModel() =
        WeatherDetailSummary(
            weatherByHourList = listOf(
                WeatherByHour(
                    date = Date(),
                    weather = Weather.SUNNY,
                    temperature = 25,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByHour(
                    date = Date().addHour(1),
                    weather = Weather.SOME_CLOUDY,
                    temperature = 23,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByHour(
                    date = Date().addHour(2),
                    weather = Weather.CLOUDY,
                    temperature = 24,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByHour(
                    date = Date().addHour(3),
                    weather = Weather.CLOUDY,
                    temperature = 16,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByHour(
                    date = Date().addHour(4),
                    weather = Weather.CLOUDY,
                    temperature = 18,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByHour(
                    date = Date().addHour(5),
                    weather = Weather.CLOUDY,
                    temperature = 24,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByHour(
                    date = Date().addHour(6),
                    weather = Weather.CLOUDY,
                    temperature = 23,
                    humidityIndex = 10,
                    isDay = true
                ),
            ),
            weatherByDateList = listOf(
                WeatherByDate(
                    date = Date(),
                    weather = Weather.CLOUDY,
                    minTemp = 20,
                    maxTemp = 35,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByDate(
                    date = Date().addDay(1),
                    weather = Weather.SOME_CLOUDY,
                    minTemp = 19,
                    maxTemp = 34,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByDate(
                    date = Date().addDay(2),
                    weather = Weather.CLOUDY,
                    minTemp = 20,
                    maxTemp = 33,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByDate(
                    date = Date().addDay(3),
                    weather = Weather.CLOUDY,
                    minTemp = 24,
                    maxTemp = 35,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByDate(
                    date = Date().addDay(4),
                    weather = Weather.CLOUDY,
                    minTemp = 21,
                    maxTemp = 28,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByDate(
                    date = Date().addDay(5),
                    weather = Weather.CLOUDY,
                    minTemp = 30,
                    maxTemp = 35,
                    humidityIndex = 10,
                    isDay = true
                ),
                WeatherByDate(
                    date = Date().addDay(6),
                    weather = Weather.CLOUDY,
                    minTemp = 21,
                    maxTemp = 35,
                    humidityIndex = 10,
                    isDay = true
                ),
            )
        )
}