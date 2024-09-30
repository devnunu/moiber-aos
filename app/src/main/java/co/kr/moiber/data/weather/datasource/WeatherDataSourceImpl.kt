package co.kr.moiber.data.weather.datasource

import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.HomeWeatherSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor() : WeatherDataSource {

    override fun getWeatherSummary(): HomeWeatherSummary? =
        FakeHomeWeatherSummary.getFakeModel()
}