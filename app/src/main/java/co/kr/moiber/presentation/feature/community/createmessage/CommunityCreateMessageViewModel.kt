package co.kr.moiber.presentation.feature.community.createmessage

import androidx.lifecycle.viewModelScope
import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.PostMessageRequest
import co.kr.moiber.model.network.onError
import co.kr.moiber.model.network.onSuccess
import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityCreateMessageViewModel @Inject constructor(
    private val communityRepository: CommunityRepository,
) : BaseViewModel<CreateMessageState, CommunityCreateMessageViewEvent, CommunityCreateMessageSideEffect>(
    initialState = CreateMessageState()
) {

    fun setInitialStateWhenModify(communityMessage: CommunityMessage) {
        setState {
            copy(
                isModify = true,
                messageId = communityMessage.id,
                upperWear = communityMessage.upperWear,
                bottomWear = communityMessage.bottomWear,
                outerWear = communityMessage.outerWear,
                temperature = communityMessage.temperature,
                message = communityMessage.text,
            )
        }
    }

    override fun onEvent(event: CommunityCreateMessageViewEvent) {
        when (event) {
            is CommunityCreateMessageViewEvent.OnSelectUpperWear -> {
                setState { copy(upperWear = event.upperWear) }
            }

            is CommunityCreateMessageViewEvent.OnSelectBottomWear -> {
                setState { copy(bottomWear = event.bottomWear) }
            }

            is CommunityCreateMessageViewEvent.OnSelectOuterWear -> {
                setState { copy(outerWear = event.outerWear) }
            }

            is CommunityCreateMessageViewEvent.OnClickStep1NextBtn -> {
                if (state.isStep1NextBtnEnable) {
                    postSideEffect(CommunityCreateMessageSideEffect.ScrollToNextPage)
                } else {
                    setState { copy(step1ErrorMsg = "※ 상의와 하의는 필수적으로 선택해야 해요.") }
                }
            }

            is CommunityCreateMessageViewEvent.OnChangeTemperature -> {
                setState { copy(temperature = event.temperature, step2Error = false) }
            }

            is CommunityCreateMessageViewEvent.OnClickStep2PreviousBtn -> {
                postSideEffect(CommunityCreateMessageSideEffect.ScrollToPreviousPage)
            }

            is CommunityCreateMessageViewEvent.OnClickStep2NextBtn -> {
                if (state.isStep2NextBtnEnable) {
                    postSideEffect(CommunityCreateMessageSideEffect.ScrollToNextPage)
                } else {
                    setState { copy(step2Error = true) }
                }
            }

            is CommunityCreateMessageViewEvent.OnChangeMessage -> {
                setState { copy(message = event.message) }
            }

            is CommunityCreateMessageViewEvent.OnClickStep3PreviousBtn -> {
                postSideEffect(CommunityCreateMessageSideEffect.ScrollToPreviousPage)
            }

            is CommunityCreateMessageViewEvent.OnClickStep3CompleteBtn -> {
                if (verifyMessage(state.message)) {
                    if (state.isModify) {
                        modifyMessage()
                    } else {
                        postNewMessage()
                    }
                }
            }

            is CommunityCreateMessageViewEvent.OnBackPressed -> {
                openDialog(CommunityCreateMessageDialogTag.CreateMessageBackPress)
            }

            is CommunityCreateMessageViewEvent.OnCloseDialog -> {
                closeDialog()
            }

            is CommunityCreateMessageViewEvent.OnClickBackPressDialogFinish -> {
                postSideEffect(CommunityCreateMessageSideEffect.PopBackStack)
                closeDialog()
            }
        }
    }

    private fun verifyMessage(message: String?): Boolean {
        val isVerified = message != null && message.length <= 45
        if (!isVerified) {
            setState { copy(step3Error = true) }
        }
        return isVerified
    }

    private fun postNewMessage() = viewModelScope.launch {
        communityRepository.postMessage(getPostMessageRequest()).collectLatest { result ->
            result.onSuccess {
                postSideEffect(CommunityCreateMessageSideEffect.PopBackStackWithSuccess)
            }.onError {

            }
        }
    }

    private fun modifyMessage() = viewModelScope.launch {
        communityRepository.modifyMessage(getPostMessageRequest()).collectLatest { result ->
            result.onSuccess {
                postSideEffect(CommunityCreateMessageSideEffect.PopBackStackWithSuccess)
            }.onError {

            }
        }
    }

    private fun getPostMessageRequest() = PostMessageRequest(
        id = state.messageId,
        userId = 0,
        temperature = state.temperature,
        message = state.message,
        upperWear = state.upperWear ?: UpperWear.Type1,
        bottomWear = state.bottomWear ?: BottomWear.Type1,
        outerWear = state.outerWear,
    )

    /**
     * Modal
     * */
    private fun openDialog(tag: CommunityCreateMessageDialogTag) {
        setState { copy(dialogState = dialogState.open(tag)) }
    }

    private fun closeDialog() {
        setState { copy(dialogState = dialogState.close()) }
    }

}