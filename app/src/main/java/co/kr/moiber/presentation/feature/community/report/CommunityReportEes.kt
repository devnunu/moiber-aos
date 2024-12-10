package co.kr.moiber.presentation.feature.community.report

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import co.kr.moiber.shared.components.model.ModalState

sealed interface CommunityReportDialogTag {
    object Complete : CommunityReportDialogTag
}

sealed interface CommunityReportViewEvent : ViewEvent {
    object OnClickCompleteDialogBtn : CommunityReportViewEvent
    object OnClickCompleteBtn : CommunityReportViewEvent
    data class OnChangeReportTxt(val reportTxt: String?) : CommunityReportViewEvent
}

sealed interface CommunityReportSideEffect : SideEffect {
    object PopBackStack : CommunityReportSideEffect
}

data class CommunityReportState(
    val reportTxt: String? = null,
    val dialogState: ModalState<CommunityReportDialogTag> =
        ModalState.Closed(CommunityReportDialogTag.Complete)
) : ViewState {

    val isBottomCtaEnable: Boolean
        get() = !reportTxt.isNullOrBlank()
}