package co.kr.moiber.presentation.home.community

import androidx.lifecycle.viewModelScope
import co.kr.moiber.data.community.repository.CommunityRepository
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
        requestCommunityContentList()
    }

    private fun requestCommunityContentList() = viewModelScope.launch {
        communityRepository.getCommunityContentList(forcedUpdate = true).collectLatest { result ->
            result.onSuccess { communityContentList ->
                setState { copy(communityContentList = communityContentList ?: emptyList()) }
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

            is HomeCommunityViewEvent.OnCloseBottomSheet -> {
                closeBottomSheet()
            }

            is HomeCommunityViewEvent.OnCloseDialog -> {
                closeDialog()
            }

            is HomeCommunityViewEvent.OnClickDialogReportBtn -> {
                openDialog(HomeCommunityDialogTag.Report)
            }

            is HomeCommunityViewEvent.OnClickDialogCompleteReportBtn -> {
                openDialog(HomeCommunityDialogTag.ReportComplete)
            }

            is HomeCommunityViewEvent.OnSelectReportReason -> {
                setState { copy(selectedReportReason = event.reportReason) }
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