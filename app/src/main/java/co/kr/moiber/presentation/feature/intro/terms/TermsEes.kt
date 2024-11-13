package co.kr.moiber.presentation.feature.intro.terms

import co.kr.moiber.data.terms.FakeTerms
import co.kr.moiber.data.terms.Terms
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface TermsSideEffect : SideEffect {
    data object NavigateToLocationPermission : TermsSideEffect
}

sealed interface TermsViewEvent : ViewEvent {
    data object OnClickAllSelectBox : TermsViewEvent
    data class OnClickTermsItem(val termsId: Int) : TermsViewEvent
    data object OnClickBottomCta : TermsViewEvent
}

data class TermsState(
    val termsList: List<Terms> = FakeTerms.getTermsList(),
    val selectedTermsList: List<Int> = listOf()
) : ViewState {

    private val requiredTermsList: List<Terms>
        get() = termsList.filter { it.isRequired }

    val isAllSelected: Boolean
        get() = requiredTermsList.all { terms ->
            selectedTermsList.contains(terms.termsId)
        }

}