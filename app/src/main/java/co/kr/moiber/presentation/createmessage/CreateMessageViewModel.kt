package co.kr.moiber.presentation.createmessage

import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateMessageViewModel @Inject constructor(
    private val communityRepository: CommunityRepository,
) : BaseViewModel<CreateMessageState, CreateMessageViewEvent, CreateMessageSideEffect>(
    initialState = CreateMessageState()
) {

    override fun onEvent(event: CreateMessageViewEvent) {
        when (event) {
            is CreateMessageViewEvent.OnSelectUpperWear -> {
                setState { copy(upperWear = event.upperWear) }
            }

            is CreateMessageViewEvent.OnSelectBottomWear -> {
                setState { copy(bottomWear = event.bottomWear) }
            }

            is CreateMessageViewEvent.OnSelectOuterWear -> {
                setState { copy(outerWear = event.outerWear) }
            }

            is CreateMessageViewEvent.OnClickFirstStepCompleteBtn -> {
                if (state.isBtnEnable) {
                    postSideEffect(CreateMessageSideEffect.ScrollToNextPage)
                } else {
                    setState { copy(firstStepErrorMsg = "※ 상의와 하의는 필수적으로 선택해야 해요.") }
                }
            }
        }
    }
}