package co.kr.moiber.presentation.feature.intro.nickname

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface NickNameSideEffect : SideEffect {
    data object NavigateToTerms : NickNameSideEffect
}

sealed interface NickNameViewEvent : ViewEvent {
    data object OnClickNextBtn : NickNameViewEvent
    data class OnChangeNickName(val nickName: String? = null) : NickNameViewEvent
}

data class NickNameState(
    val nickName: String? = null
) : ViewState {
}