package co.kr.moiber.presentation.feature.weather.detail

import co.kr.moiber.data.weather.repository.WeatherRepository
import co.kr.moiber.presentation.feature.weather.location.WeatherSelectLocationSideEffect
import co.kr.moiber.presentation.feature.weather.location.WeatherSelectLocationState
import co.kr.moiber.presentation.feature.weather.location.WeatherSelectLocationViewEvent
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<WeatherDetailState, WeatherDetailViewEvent, WeatherDetailSideEffect>(
    initialState = WeatherDetailState()
) {

    override fun onEvent(event: WeatherDetailViewEvent) {
        when (event) {
            else -> Unit
        }
    }
}