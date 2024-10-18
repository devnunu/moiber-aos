package co.kr.moiber.presentation.home.community

import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.presentation.home.community.components.popup.ReportReason
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import co.kr.moiber.shared.components.model.ModalState

sealed interface HomeCommunityDialogTag {
    data class LongPress(val message: CommunityContent) : HomeCommunityDialogTag
    data object Report : HomeCommunityDialogTag
    data object ReportComplete : HomeCommunityDialogTag
}

sealed interface HomeCommunityBottomSheetTag {
    data object WeatherDetail : HomeCommunityBottomSheetTag
}

sealed interface HomeCommunityViewEvent : ViewEvent {
    data class OnChangeCommunityMyHistory(val checked: Boolean) : HomeCommunityViewEvent
    data object OnClickEditFloatingBtn : HomeCommunityViewEvent
    data class OnClickMessageItem(val message: CommunityContent) : HomeCommunityViewEvent
    data class OnLongClickMessageItem(val message: CommunityContent) : HomeCommunityViewEvent
    data object OnCloseBottomSheet : HomeCommunityViewEvent
    data object OnCloseDialog : HomeCommunityViewEvent
    data object OnClickDialogReportBtn : HomeCommunityViewEvent
    data class OnClickDialogCompleteReportBtn(val temp: String) : HomeCommunityViewEvent
    data class OnSelectReportReason(val reportReason: ReportReason) : HomeCommunityViewEvent
}

sealed interface HomeCommunitySideEffect : SideEffect {

}

data class HomeCommunityState(
    val isDay: Boolean = true,
    val isOnMyHistory: Boolean = false,
    val communityContentList: List<CommunityContent> = emptyList(),
    val selectedReportReason: ReportReason = ReportReason.CASE1,
    val dialogState: ModalState<HomeCommunityDialogTag> =
        ModalState.Closed(HomeCommunityDialogTag.Report),
    val bottomSheetState: ModalState<HomeCommunityBottomSheetTag> =
        ModalState.Closed(HomeCommunityBottomSheetTag.WeatherDetail)
) : ViewState {
}