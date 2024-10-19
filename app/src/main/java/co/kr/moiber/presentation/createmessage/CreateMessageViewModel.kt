package co.kr.moiber.presentation.createmessage

import androidx.lifecycle.viewModelScope
import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.model.community.PostMessageRequest
import co.kr.moiber.model.network.onError
import co.kr.moiber.model.network.onSuccess
import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

            is CreateMessageViewEvent.OnClickStep1NextBtn -> {
                if (state.isStep1NextBtnEnable) {
                    postSideEffect(CreateMessageSideEffect.ScrollToNextPage)
                } else {
                    setState { copy(step1ErrorMsg = "※ 상의와 하의는 필수적으로 선택해야 해요.") }
                }
            }

            is CreateMessageViewEvent.OnChangeTemperature -> {
                setState { copy(temperature = event.temperature, step2Error = false) }
            }

            is CreateMessageViewEvent.OnClickStep2PreviousBtn -> {
                postSideEffect(CreateMessageSideEffect.ScrollToPreviousPage)
            }

            is CreateMessageViewEvent.OnClickStep2NextBtn -> {
                if (state.isStep2NextBtnEnable) {
                    postSideEffect(CreateMessageSideEffect.ScrollToNextPage)
                } else {
                    setState { copy(step2Error = true) }
                }
            }

            is CreateMessageViewEvent.OnChangeMessage -> {
                setState { copy(message = event.message) }
            }

            is CreateMessageViewEvent.OnClickStep3PreviousBtn -> {
                postSideEffect(CreateMessageSideEffect.ScrollToPreviousPage)
            }

            is CreateMessageViewEvent.OnClickStep3CompleteBtn -> {
                val message = state.message
                if (message != null && message.length >= 45) {
                    setState { copy(step3Error = true) }
                } else {
                    postNewMessage()
                }
            }
        }
    }

    private fun postNewMessage() = viewModelScope.launch {
        communityRepository.postMessage(getPostMessageRequest()).collectLatest { result ->
            result.onSuccess {
                postSideEffect(CreateMessageSideEffect.PopBackStackWithSuccess)
            }.onError {

            }
        }
    }

    private fun getPostMessageRequest() = PostMessageRequest(
        userId = 0,
        temperature = state.temperature,
        message = state.message,
        upperWear = state.upperWear ?: UpperWear.Type1,
        bottomWear = state.bottomWear ?: BottomWear.Type1,
        outerWear = state.outerWear,
    )
}