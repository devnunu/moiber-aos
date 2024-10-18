package co.kr.moiber.presentation.home.community

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.presentation.home.community.components.popup.ReportCase
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import co.kr.moiber.shared.components.model.ModalState

sealed interface HomeCommunityDialogTag {
    data class LongPress(val message: CommunityMessage) : HomeCommunityDialogTag
    data class DeleteMessage(val message: CommunityMessage) : HomeCommunityDialogTag
    data class Report(val message: CommunityMessage) : HomeCommunityDialogTag
    data object ReportComplete : HomeCommunityDialogTag
}

sealed interface HomeCommunityBottomSheetTag {
    data object WeatherDetail : HomeCommunityBottomSheetTag
}

sealed interface HomeCommunityViewEvent : ViewEvent {
    data class OnChangeCommunityMyHistory(val checked: Boolean) : HomeCommunityViewEvent
    data object OnClickEditFloatingBtn : HomeCommunityViewEvent
    data class OnClickMessageItem(val message: CommunityMessage) : HomeCommunityViewEvent
    data class OnLongClickMessageItem(val message: CommunityMessage) : HomeCommunityViewEvent

    /** LongPressPopUp */
    data class OnClickDialogLikeBtn(val message: CommunityMessage) : HomeCommunityViewEvent
    data class OnClickDialogReportBtn(val message: CommunityMessage) : HomeCommunityViewEvent
    data class OnClickDialogDeleteBtn(val message: CommunityMessage) : HomeCommunityViewEvent

    /** ReportPopUp */
    data class OnClickDialogCompleteReportBtn(val message: CommunityMessage) :
        HomeCommunityViewEvent

    data class OnSelectReportCase(val reportCase: ReportCase) : HomeCommunityViewEvent
    data class OnChangeReportReasonText(val text: String) : HomeCommunityViewEvent

    /** DeletePopUp */
    data class OnClickDialogFinalDeleteBtn(val message: CommunityMessage) : HomeCommunityViewEvent

    /** Common Modal */
    data object OnCloseBottomSheet : HomeCommunityViewEvent
    data object OnCloseDialog : HomeCommunityViewEvent
}

sealed interface HomeCommunitySideEffect : SideEffect {
    data class ShowToast(val message: String) : HomeCommunitySideEffect
}

data class HomeCommunityState(
    val isDay: Boolean = true,
    val isOnMyHistory: Boolean = false,
    val communityMessageList: List<CommunityMessage> = emptyList(),
    /** 신고하기 */
    val selectedReportCaseList: List<ReportCase> = emptyList(),
    val reportReason: String? = null,
    /** Modal */
    val dialogState: ModalState<HomeCommunityDialogTag> =
        ModalState.Closed(HomeCommunityDialogTag.ReportComplete),
    val bottomSheetState: ModalState<HomeCommunityBottomSheetTag> =
        ModalState.Closed(HomeCommunityBottomSheetTag.WeatherDetail)
) : ViewState {
}