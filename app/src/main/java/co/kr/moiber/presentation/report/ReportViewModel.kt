package co.kr.moiber.presentation.report

import co.kr.moiber.presentation.home.HomeState
import co.kr.moiber.presentation.home.HomeViewEvent
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor() : BaseViewModel<ReportState, ReportViewEvent>(
    initialState = ReportState()
) {
    override fun onEvent(event: ReportViewEvent) {
//        when(event) {
//
//        }
    }
}