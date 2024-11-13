package co.kr.moiber.presentation.feature.intro.nickname

import co.kr.moiber.presentation.feature.intro.login.LoginSideEffect
import co.kr.moiber.presentation.feature.intro.login.LoginState
import co.kr.moiber.presentation.feature.intro.login.LoginViewEvent
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NickNameViewModel @Inject constructor() :
    BaseViewModel<NickNameState, NickNameViewEvent, NickNameSideEffect>(
        initialState = NickNameState()
    ) {

    override fun onEvent(event: NickNameViewEvent) {
        when (event) {
            is NickNameViewEvent.OnClickNextBtn -> {

            }

            is NickNameViewEvent.OnChangeNickName -> {
                setState { copy(nickName = event.nickName) }
            }
        }
    }
}