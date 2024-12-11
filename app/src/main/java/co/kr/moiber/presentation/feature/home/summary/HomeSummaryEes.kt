package co.kr.moiber.presentation.feature.home.summary

import co.kr.moiber.model.weather.HomeWeatherSummary
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface HomeSummaryViewEvent : ViewEvent {
    data object OnClickWeatherContent : HomeSummaryViewEvent
}

sealed interface HomeSummarySideEffect : SideEffect {
    data object NavigateToWeatherDetail:HomeSummarySideEffect
}

data class HomeSummaryState(
    val weatherSummary: HomeWeatherSummary? = null,
) : ViewState {
}