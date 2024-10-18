package co.kr.moiber.presentation.createmessage

import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface CreateMessageViewEvent : ViewEvent {
    /** FirstStep */
    data class OnSelectUpperWear(val upperWear: UpperWear?) : CreateMessageViewEvent
    data class OnSelectBottomWear(val bottomWear: BottomWear?) : CreateMessageViewEvent
    data class OnSelectOuterWear(val outerWear: OuterWear?) : CreateMessageViewEvent
    data object OnClickFirstStepCompleteBtn : CreateMessageViewEvent
}

sealed interface CreateMessageSideEffect : SideEffect {

}

data class CreateMessageState(
    val upperWear: UpperWear? = null,
    val bottomWear: BottomWear? = null,
    val outerWear: OuterWear? = null,
    val firstStepErrorMsg: String? = null
) : ViewState {

    val isBtnEnable: Boolean
        get() = upperWear != null && bottomWear != null
}