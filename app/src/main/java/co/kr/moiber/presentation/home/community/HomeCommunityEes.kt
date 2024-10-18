package co.kr.moiber.presentation.home.community

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.presentation.home.community.components.popup.ReportReason
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import co.kr.moiber.shared.components.model.ModalState

sealed interface HomeCommunityDialogTag {
    data class LongPress(val message: CommunityMessage) : HomeCommunityDialogTag
    data object Report : HomeCommunityDialogTag
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
    data object OnClickDialogReportBtn : HomeCommunityViewEvent

    /** ReportPopUp */
    data class OnClickDialogCompleteReportBtn(val temp: String) : HomeCommunityViewEvent
    data class OnSelectReportReason(val reportReason: ReportReason) : HomeCommunityViewEvent

    /** Common Modal */
    data object OnCloseBottomSheet : HomeCommunityViewEvent
    data object OnCloseDialog : HomeCommunityViewEvent
}

sealed interface HomeCommunitySideEffect : SideEffect {

}

data class HomeCommunityState(
    val isDay: Boolean = true,
    val isOnMyHistory: Boolean = false,
    val communityMessageList: List<CommunityMessage> = emptyList(),
    val selectedReportReason: ReportReason = ReportReason.CASE1,
    val dialogState: ModalState<HomeCommunityDialogTag> =
        ModalState.Closed(HomeCommunityDialogTag.Report),
    val bottomSheetState: ModalState<HomeCommunityBottomSheetTag> =
        ModalState.Closed(HomeCommunityBottomSheetTag.WeatherDetail)
) : ViewState {
}