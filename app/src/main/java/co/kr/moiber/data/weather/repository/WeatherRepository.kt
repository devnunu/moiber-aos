package co.kr.moiber.data.weather.repository

import co.kr.moiber.model.network.ResResult
import co.kr.moiber.model.weather.HomeWeatherSummary
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherSummary(): Flow<ResResult<HomeWeatherSummary>>
}