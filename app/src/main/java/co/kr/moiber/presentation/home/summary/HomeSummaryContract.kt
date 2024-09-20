package co.kr.moiber.presentation.home.summary

import co.kr.moiber.model.FakeHomeWeatherSummary
import co.kr.moiber.model.HomeWeatherSummary
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed class HomeSummaryViewEvent : ViewEvent {

}

data class HomeSummaryState(
    val weatherSummary: HomeWeatherSummary? = FakeHomeWeatherSummary.getFakeModel()
) : ViewState {
}