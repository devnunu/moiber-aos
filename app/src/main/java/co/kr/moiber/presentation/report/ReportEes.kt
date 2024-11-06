package co.kr.moiber.presentation.report

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import co.kr.moiber.shared.components.model.ModalState

sealed interface ReportDialogTag {
    object Complete : ReportDialogTag
}

sealed interface ReportViewEvent : ViewEvent {
    object OnClickCompleteDialogBtn : ReportViewEvent
    object OnClickCompleteBtn : ReportViewEvent
    data class OnChangeReportTxt(val reportTxt: String?) : ReportViewEvent
}

sealed interface ReportSideEffect : SideEffect {
    object PopBackStack : ReportSideEffect
}

data class ReportState(
    val reportTxt: String? = null,
    val dialogState: ModalState<ReportDialogTag> =
        ModalState.Closed(ReportDialogTag.Complete)
) : ViewState {

    val isBottomCtaEnable: Boolean
        get() = !reportTxt.isNullOrBlank()
}