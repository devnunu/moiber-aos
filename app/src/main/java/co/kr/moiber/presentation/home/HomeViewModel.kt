package co.kr.moiber.presentation.home

import androidx.lifecycle.viewModelScope
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
) : BaseViewModel<HomeState, HomeViewEvent, HomeSideEffect>(
    initialState = HomeState()
) {
    init {
        requestWeatherSummary()
    }

    private fun requestWeatherSummary() = viewModelScope.launch {
        weatherRepository.getWeatherSummary().collectLatest { result ->
            result.onSuccess { weatherSummary ->
                setState { copy(weatherSummary = weatherSummary) }
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

        }
    }
}