package co.kr.moiber.data.weather.repository

import co.kr.moiber.data.weather.datasource.WeatherDataSourceImpl
import co.kr.moiber.model.network.ResResult
import co.kr.moiber.model.network.asResult
import co.kr.moiber.model.weather.HomeWeatherSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataSource: WeatherDataSourceImpl
) : WeatherRepository {

    override fun getWeatherSummary(): Flow<ResResult<HomeWeatherSummary>> = flow {
        val result = remoteWeatherDataSource.getWeatherSummary()
        emit(result)
    }.mapNotNull { it }
        .asResult()
}