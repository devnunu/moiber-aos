package co.kr.moiber.presentation.home

import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed class HomeViewEvent : ViewEvent {

}

data class HomeState(
    val temp: String? = null
) : ViewState {
}