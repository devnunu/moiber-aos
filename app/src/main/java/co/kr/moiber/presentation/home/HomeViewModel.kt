package co.kr.moiber.presentation.home

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeState, HomeViewEvent, HomeSideEffect>(
    initialState = HomeState()
) {
    override fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.OnChangeCommunityMyHistory -> {
                setState { copy(isOnMyHistory = !state.isOnMyHistory) }
            }

            is HomeViewEvent.OnClickEditFloatingBtn -> {
                val weatherSummary = state.weatherSummary?.copy(
                    isDay = state.weatherSummary?.isDay?.not() ?: true
                )
                setState { copy(weatherSummary = weatherSummary) }
            }

        }
    }
}