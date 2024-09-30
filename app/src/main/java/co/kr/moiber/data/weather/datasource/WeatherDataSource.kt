package co.kr.moiber.data.weather.datasource

import co.kr.moiber.model.network.ResResult
import co.kr.moiber.model.weather.HomeWeatherSummary
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    fun getWeatherSummary(): HomeWeatherSummary?
}