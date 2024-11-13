package co.kr.moiber.presentation.feature.intro.terms

import co.kr.moiber.presentation.feature.intro.nickname.NickNameSideEffect
import co.kr.moiber.presentation.feature.intro.nickname.NickNameState
import co.kr.moiber.presentation.feature.intro.nickname.NickNameViewEvent
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TermsViewModel @Inject constructor() :
    BaseViewModel<TermsState, TermsViewEvent, TermsSideEffect>(
        initialState = TermsState()
    ) {
    override fun onEvent(event: TermsViewEvent) {
        when (event) {
            is TermsViewEvent.OnClickAllSelectBox -> {
                val selectedTermsList = state.selectedTermsList.toMutableList()
                if (selectedTermsList.isEmpty()) {
                    selectedTermsList.addAll(state.termsList.map { it.termsId })
                } else {
                    selectedTermsList.clear()
                }
                setState { copy(selectedTermsList = selectedTermsList) }
            }

            is TermsViewEvent.OnClickTermsItem -> {
                val termsId = event.termsId
                val selectedTermsList = state.selectedTermsList.toMutableList()
                if (selectedTermsList.contains(termsId)) {
                    selectedTermsList.remove(termsId)
                } else {
                    selectedTermsList.add(termsId)
                }
                setState { copy(selectedTermsList = selectedTermsList) }
            }

            is TermsViewEvent.OnClickBottomCta -> {
                postSideEffect(TermsSideEffect.NavigateToLocationPermission)
            }
        }
    }
}