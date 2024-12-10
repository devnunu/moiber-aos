package co.kr.moiber.presentation.feature.community.report

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommunityReportViewModel @Inject constructor() :
    BaseViewModel<CommunityReportState, CommunityReportViewEvent, CommunityReportSideEffect>(
        initialState = CommunityReportState()
    ) {
    override fun onEvent(event: CommunityReportViewEvent) {
        when (event) {
            is CommunityReportViewEvent.OnClickCompleteDialogBtn -> {
                setState { copy(dialogState = dialogState.close()) }
                postSideEffect(CommunityReportSideEffect.PopBackStack)
            }

            is CommunityReportViewEvent.OnClickCompleteBtn -> {
                // TODO: API 요청
                setState { copy(dialogState = dialogState.open(CommunityReportDialogTag.Complete)) }
            }

            is CommunityReportViewEvent.OnChangeReportTxt -> {
                setState { copy(reportTxt = event.reportTxt) }
            }
        }
    }

}