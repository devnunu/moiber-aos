package co.kr.moiber.presentation.home

import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.HomeWeatherSummary
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState


sealed interface HomeViewEvent : ViewEvent {
    data class OnChangeCommunityMyHistory(val checked: Boolean) : HomeViewEvent
    object OnClickEditFloatingBtn : HomeViewEvent
    data class OnClickMessageItem(val message: CommunityContent) : HomeViewEvent
    data class OnLongClickMessageItem(val message: CommunityContent) : HomeViewEvent
}

sealed interface HomeSideEffect : SideEffect {

}



data class HomeState(
    val isOnMyHistory: Boolean = false,
    val weatherSummary: HomeWeatherSummary? = null,
    val communityContentList: List<CommunityContent> = emptyList()
) : ViewState {
}