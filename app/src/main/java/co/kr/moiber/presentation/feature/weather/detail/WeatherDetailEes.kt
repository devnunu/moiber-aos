package co.kr.moiber.presentation.feature.weather.detail

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface WeatherDetailSideEffect: SideEffect {

}

sealed interface WeatherDetailViewEvent: ViewEvent {

}

data class WeatherDetailState(
    val temp: String? = null
) : ViewState {
}