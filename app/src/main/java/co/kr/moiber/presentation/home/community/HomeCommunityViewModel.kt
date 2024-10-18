package co.kr.moiber.presentation.home.community

import androidx.lifecycle.viewModelScope
import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.network.onSuccess
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeCommunityViewModel @Inject constructor(
    private val communityRepository: CommunityRepository,
) : BaseViewModel<HomeCommunityState, HomeCommunityViewEvent, HomeCommunitySideEffect>(
    initialState = HomeCommunityState()
) {

    init {
        requestCommunityMessageList()
    }

    private fun requestCommunityMessageList() = viewModelScope.launch {
        communityRepository.getMessageList(forcedUpdate = true).collectLatest { result ->
            result.onSuccess { communityMessageList ->
                setState { copy(communityMessageList = communityMessageList ?: emptyList()) }
            }
        }
    }

    override fun onEvent(event: HomeCommunityViewEvent) {
        when (event) {
            is HomeCommunityViewEvent.OnChangeCommunityMyHistory -> {
                setState { copy(isOnMyHistory = !state.isOnMyHistory) }
            }

            is HomeCommunityViewEvent.OnClickEditFloatingBtn -> {
//                val weatherSummary = state.weatherSummary?.copy(
//                    isDay = state.weatherSummary?.isDay?.not() ?: true
//                )
//                setState { copy(weatherSummary = weatherSummary) }
            }

            is HomeCommunityViewEvent.OnClickMessageItem -> {
                openBottomSheet(HomeCommunityBottomSheetTag.WeatherDetail)
            }

            is HomeCommunityViewEvent.OnLongClickMessageItem -> {
                openDialog(HomeCommunityDialogTag.LongPress(message = event.message))
            }

            /** LongPressPopUp */
            is HomeCommunityViewEvent.OnClickDialogReportBtn -> {
                openDialog(HomeCommunityDialogTag.Report)
            }

            is HomeCommunityViewEvent.OnClickDialogLikeBtn -> {
                postMessageLike(contentMessage = event.message)
                closeDialog()
            }

            /** ReportPopUp */
            is HomeCommunityViewEvent.OnClickDialogCompleteReportBtn -> {
                openDialog(HomeCommunityDialogTag.ReportComplete)
            }

            is HomeCommunityViewEvent.OnSelectReportReason -> {
                val reportReason = event.reportReason
                val selectedReportReasonList = state.selectedReportReasonList.toMutableList()
                if (selectedReportReasonList.contains(reportReason)) {
                    selectedReportReasonList.remove(reportReason)
                } else {
                    selectedReportReasonList.add(reportReason)
                }
                setState { copy(selectedReportReasonList = selectedReportReasonList) }
            }

            /** Common Modal */
            is HomeCommunityViewEvent.OnCloseBottomSheet -> {
                closeBottomSheet()
            }

            is HomeCommunityViewEvent.OnCloseDialog -> {
                closeDialog()
            }
        }
    }

    private fun postMessageLike(contentMessage: CommunityMessage) = viewModelScope.launch {
        communityRepository.postMessageLike(contentMessage).collectLatest { result -> }
    }

    /**
     * Modal
     * */
    private fun openDialog(tag: HomeCommunityDialogTag) {
        setState { copy(dialogState = dialogState.open(tag)) }
    }

    private fun closeDialog() {
        setState { copy(dialogState = dialogState.close()) }
    }

    private fun openBottomSheet(tag: HomeCommunityBottomSheetTag) {
        setState { copy(bottomSheetState = bottomSheetState.open(tag)) }
    }

    private fun closeBottomSheet() {
        setState { copy(bottomSheetState = bottomSheetState.close()) }
    }
}