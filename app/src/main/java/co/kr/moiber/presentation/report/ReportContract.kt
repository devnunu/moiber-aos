package co.kr.moiber.presentation.report

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed class ReportViewEvent : ViewEvent {
}

sealed class ReportSideEffect : SideEffect {
}

data class ReportState(
    val temp: String? = null
) : ViewState {
}