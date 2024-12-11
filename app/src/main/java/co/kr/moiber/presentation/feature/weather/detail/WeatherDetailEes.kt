package co.kr.moiber.presentation.feature.weather.detail

import co.kr.moiber.model.weather.HomeWeatherSummary
import co.kr.moiber.model.weather.WeatherDetailSummary
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface WeatherDetailSideEffect : SideEffect {
    data object NavigateToWeatherSelectLocation : WeatherDetailSideEffect
}

sealed interface WeatherDetailViewEvent : ViewEvent {
    data object OnClickTopHeaderLocation : WeatherDetailViewEvent
}

data class WeatherDetailState(
    val weatherSummary: HomeWeatherSummary? = null,
    val weatherDetail: WeatherDetailSummary? = null
) : ViewState {
}