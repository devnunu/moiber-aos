package co.kr.moiber.presentation.feature.intro.welcome

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface WelcomeSideEffect : SideEffect {
    object NavigateToMainHome : WelcomeSideEffect
}

sealed interface WelcomeViewEvent : ViewEvent {
    object OnClickGoToHomeBtn : WelcomeViewEvent
}

data class WelcomeState(
    val temp: String? = null
) : ViewState {
}