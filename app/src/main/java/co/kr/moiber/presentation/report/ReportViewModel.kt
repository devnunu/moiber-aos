package co.kr.moiber.presentation.report

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor() :
    BaseViewModel<ReportState, ReportViewEvent, ReportSideEffect>(
        initialState = ReportState()
    ) {
    override fun onEvent(event: ReportViewEvent) {
        when (event) {
            is ReportViewEvent.OnClickCompleteDialogBtn -> {
                setState { copy(dialogState = dialogState.close()) }
                postSideEffect(ReportSideEffect.PopBackStack)
            }

            is ReportViewEvent.OnClickCompleteBtn -> {
                // TODO: API 요청
                setState { copy(dialogState = dialogState.open(ReportDialogTag.Complete)) }
            }

            is ReportViewEvent.OnChangeReportTxt -> {
                setState { copy(reportTxt = event.reportTxt) }
            }
        }
    }

}