package co.kr.moiber.presentation.createmessage

import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.presentation.home.community.HomeCommunitySideEffect
import co.kr.moiber.presentation.home.community.HomeCommunityState
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
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
            else -> Unit
        }
    }
}