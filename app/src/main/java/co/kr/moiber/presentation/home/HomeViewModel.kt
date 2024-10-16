package co.kr.moiber.presentation.home

import androidx.lifecycle.viewModelScope
import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.data.weather.repository.WeatherRepository
import co.kr.moiber.model.network.onSuccess
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val communityRepository: CommunityRepository,
) : BaseViewModel<HomeState, HomeViewEvent, HomeSideEffect>(
    initialState = HomeState()
) {
    init {
        requestWeatherSummary()
        requestCommunityContentList()
    }

    private fun requestWeatherSummary() = viewModelScope.launch {
        weatherRepository.getWeatherSummary().collectLatest { result ->
            result.onSuccess { weatherSummary ->
                setState { copy(weatherSummary = weatherSummary) }
            }
        }
    }

    private fun requestCommunityContentList() = viewModelScope.launch {
        communityRepository.getCommunityContentList().collectLatest { result ->
            result.onSuccess { communityContentList ->
                setState { copy(communityContentList = communityContentList ?: emptyList()) }
            }
        }
    }

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

            is HomeViewEvent.OnClickMessageItem -> {
                openBottomSheet(HomeViewBottomSheetTag.CommunityWeatherDetail)
            }

            is HomeViewEvent.OnLongClickMessageItem -> {
                openDialog(HomeViewDialogTag.CommunityLongPress)
            }

            is HomeViewEvent.OnCloseBottomSheet -> {
                closeBottomSheet()
            }

            is HomeViewEvent.OnCloseDialog -> {
                closeDialog()
            }

            is HomeViewEvent.OnClickDialogReportBtn -> {
                openDialog(HomeViewDialogTag.CommunityReport)
            }

            is HomeViewEvent.OnClickDialogCompleteReportBtn -> {
                openDialog(HomeViewDialogTag.CommunityReportComplete)
            }

            is HomeViewEvent.OnSelectReportReason -> {
                setState { copy(selectedReportReason = event.reportReason) }
            }
        }
    }

    /**
     * Modal
     * */
    private fun openDialog(tag: HomeViewDialogTag) {
        setState { copy(dialogState = dialogState.open(tag)) }
    }

    private fun closeDialog() {
        setState { copy(dialogState = dialogState.close()) }
    }

    private fun openBottomSheet(tag: HomeViewBottomSheetTag) {
        setState { copy(bottomSheetState = bottomSheetState.open(tag)) }
    }

    private fun closeBottomSheet() {
        setState { copy(bottomSheetState = bottomSheetState.close()) }
    }
}