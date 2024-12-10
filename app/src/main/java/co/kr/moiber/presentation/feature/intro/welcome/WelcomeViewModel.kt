package co.kr.moiber.presentation.feature.intro.welcome

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() :
    BaseViewModel<WelcomeState, WelcomeViewEvent, WelcomeSideEffect>(
        initialState = WelcomeState()
    ) {

    override fun onEvent(event: WelcomeViewEvent) {
        when (event) {
            is WelcomeViewEvent.OnClickGoToHomeBtn -> {
                postSideEffect(WelcomeSideEffect.NavigateToMainHome)
            }

            else -> Unit
        }
    }
}