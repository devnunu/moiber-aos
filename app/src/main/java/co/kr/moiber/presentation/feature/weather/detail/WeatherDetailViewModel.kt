package co.kr.moiber.presentation.feature.weather.detail

import androidx.lifecycle.viewModelScope
import co.kr.moiber.data.weather.repository.WeatherRepository
import co.kr.moiber.model.network.onSuccess
import co.kr.moiber.model.weather.FakeWeatherDetail
import co.kr.moiber.presentation.feature.weather.location.WeatherSelectLocationSideEffect
import co.kr.moiber.presentation.feature.weather.location.WeatherSelectLocationState
import co.kr.moiber.presentation.feature.weather.location.WeatherSelectLocationViewEvent
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<WeatherDetailState, WeatherDetailViewEvent, WeatherDetailSideEffect>(
    initialState = WeatherDetailState()
) {

    init {
        requestWeatherSummary()
        requestWeatherDetailSummary()
    }

    private fun requestWeatherSummary() = viewModelScope.launch {
        weatherRepository.getWeatherSummary().collectLatest { result ->
            result.onSuccess { weatherSummary ->
                setState { copy(weatherSummary = weatherSummary) }
            }
        }
    }

    private fun requestWeatherDetailSummary() = viewModelScope.launch {
        setState { copy(weatherDetail = FakeWeatherDetail.getFakeModel()) }
    }

    override fun onEvent(event: WeatherDetailViewEvent) {
        when (event) {
            is WeatherDetailViewEvent.OnClickTopHeaderLocation -> {
                postSideEffect(WeatherDetailSideEffect.NavigateToWeatherSelectLocation)
            }
        }
    }
}