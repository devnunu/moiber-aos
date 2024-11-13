package co.kr.moiber.presentation.feature.intro.login

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<LoginState, LoginViewEvent, LoginSideEffect>(
        initialState = LoginState()
    ) {
    override fun onEvent(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.OnClickLoginBtn -> {
                postSideEffect(LoginSideEffect.NavigateToNickName)
            }
        }
    }
}