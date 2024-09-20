package co.kr.moiber.presentation.home.summary

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeSummaryViewModel @Inject constructor() : BaseViewModel<HomeSummaryState, HomeSummaryViewEvent>(
    HomeSummaryState()
) {
    override fun onEvent(event: HomeSummaryViewEvent) {
        when (event) {
            else -> Unit
        }
    }
}