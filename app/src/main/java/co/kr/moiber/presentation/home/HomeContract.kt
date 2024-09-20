package co.kr.moiber.presentation.home

import co.kr.moiber.model.FakeHomeWeatherSummary
import co.kr.moiber.model.HomeWeatherSummary
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed class HomeViewEvent : ViewEvent {

}

data class HomeState(
    val weatherSummary: HomeWeatherSummary? = FakeHomeWeatherSummary.getFakeModel()
) : ViewState {
}