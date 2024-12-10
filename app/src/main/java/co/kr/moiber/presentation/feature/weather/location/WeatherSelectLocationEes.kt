package co.kr.moiber.presentation.feature.weather.location

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface WeatherSelectLocationSideEffect : SideEffect {

}

sealed interface WeatherSelectLocationViewEvent : ViewEvent {
    data class OnChangedLocationText(val text: String) : WeatherSelectLocationViewEvent
}

data class WeatherSelectLocationState(
    val locationText: String? = null
) : ViewState