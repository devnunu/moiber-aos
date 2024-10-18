package co.kr.moiber.presentation.createmessage

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface CreateMessageViewEvent : ViewEvent {

}

sealed interface CreateMessageSideEffect : SideEffect {

}

data class CreateMessageState(
    val temp: String? = null
) : ViewState {
}