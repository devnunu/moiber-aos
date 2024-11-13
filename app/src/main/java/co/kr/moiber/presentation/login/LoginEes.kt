package co.kr.moiber.presentation.login

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface LoginSideEffect : SideEffect {
    data object RequestPermission: LoginSideEffect
    data object NavigateToNickName: LoginSideEffect
}

sealed interface LoginViewEvent : ViewEvent {
    data object OnClickLoginBtn : LoginViewEvent
}

data class LoginState(
    val temp: String? = null
) : ViewState