package co.kr.moiber.presentation.location

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface SelectLocationSideEffect : SideEffect {

}

sealed interface SelectLocationViewEvent : ViewEvent {
    data class OnChangedLocationText(val text: String) : SelectLocationViewEvent
}

data class SelectLocationState(
    val locationText: String? = null
) : ViewState