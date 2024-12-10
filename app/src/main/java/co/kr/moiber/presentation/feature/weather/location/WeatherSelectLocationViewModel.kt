package co.kr.moiber.presentation.feature.weather.location

import co.kr.moiber.data.weather.repository.WeatherRepository
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherSelectLocationViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<WeatherSelectLocationState, WeatherSelectLocationViewEvent, WeatherSelectLocationSideEffect>(
    initialState = WeatherSelectLocationState()
) {
    override fun onEvent(event: WeatherSelectLocationViewEvent) {
        when (event) {
            is WeatherSelectLocationViewEvent.OnChangedLocationText -> {
                setState { copy(locationText = event.text) }
            }

            else -> Unit
        }
    }
}