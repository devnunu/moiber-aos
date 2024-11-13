package co.kr.moiber.presentation.feature.weather.location

import co.kr.moiber.data.weather.repository.WeatherRepository
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectLocationViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<SelectLocationState, SelectLocationViewEvent, SelectLocationSideEffect>(
    initialState = SelectLocationState()
) {
    override fun onEvent(event: SelectLocationViewEvent) {
        when (event) {
            is SelectLocationViewEvent.OnChangedLocationText -> {
                setState { copy(locationText = event.text) }
            }

            else -> Unit
        }
    }
}