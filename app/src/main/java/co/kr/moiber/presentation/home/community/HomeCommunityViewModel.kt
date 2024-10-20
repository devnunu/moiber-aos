package co.kr.moiber.presentation.home.community

import androidx.lifecycle.viewModelScope
import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.ReportRequest
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

            is HomeCommunityViewEvent.OnClickMessageItem -> {
                openBottomSheet(HomeCommunityBottomSheetTag.WeatherDetail)
            }

            is HomeCommunityViewEvent.OnLongClickMessageItem -> {
                openDialog(HomeCommunityDialogTag.LongPress(message = event.message))
            }

            is HomeCommunityViewEvent.OnResultSuccessCreateMessage -> {
                openDialog(HomeCommunityDialogTag.PostMessageComplete)
            }

            /** LongPressPopUp */
            is HomeCommunityViewEvent.OnClickDialogReportBtn -> {
                openDialog(HomeCommunityDialogTag.Report(event.message))
            }

            is HomeCommunityViewEvent.OnClickDialogLikeBtn -> {
                postMessageLike(message = event.message)
                closeDialog()
            }

            is HomeCommunityViewEvent.OnClickDialogModifyBtn -> {
                postSideEffect(HomeCommunitySideEffect.NavigateToCreateMessage(event.message))
                closeDialog()
            }

            is HomeCommunityViewEvent.OnClickDialogDeleteBtn -> {
                openDialog(HomeCommunityDialogTag.DeleteMessage(event.message))
            }

            /** ReportPopUp */
            is HomeCommunityViewEvent.OnSelectReportCase -> {
                val reportCase = event.reportCase
                val selectedReportCaseList = state.selectedReportCaseList.toMutableList()
                if (selectedReportCaseList.contains(reportCase)) {
                    selectedReportCaseList.remove(reportCase)
                } else {
                    selectedReportCaseList.add(reportCase)
                }
                setState { copy(selectedReportCaseList = selectedReportCaseList) }
            }

            is HomeCommunityViewEvent.OnChangeReportReasonText -> {
                setState { copy(reportReason = event.text) }
            }

            is HomeCommunityViewEvent.OnClickDialogCompleteReportBtn -> {
                portMessageReport(event.message)
                openDialog(HomeCommunityDialogTag.ReportComplete)
            }

            /** DeletePopUP */
            is HomeCommunityViewEvent.OnClickDialogFinalDeleteBtn -> {
                deleteMessage(event.message)
                closeDialog()
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

    private fun postMessageLike(message: CommunityMessage) = viewModelScope.launch {
        communityRepository.postMessageLike(message).collectLatest { result -> }
    }

    private fun portMessageReport(message: CommunityMessage) = viewModelScope.launch {
        val reportRequest = ReportRequest(
            reportCaseList = state.selectedReportCaseList,
            reportReason = state.reportReason
        )
        communityRepository.postMessageReport(
            message = message,
            reportRequest = reportRequest
        ).collectLatest { result ->
            result.onSuccess {
                // clear
                setState { copy(selectedReportCaseList = listOf(), reportReason = null) }
            }
        }
    }

    private fun deleteMessage(message: CommunityMessage) = viewModelScope.launch {
        communityRepository.deleteMessage(message).collectLatest { result ->
            result.onSuccess {
                postSideEffect(HomeCommunitySideEffect.ShowToast("게시글 삭제 완료!"))
            }
        }
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