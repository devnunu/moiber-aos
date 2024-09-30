package co.kr.moiber.presentation.home

import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.HomeWeatherSummary
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface HomeSideEffect : SideEffect {

}

sealed interface HomeViewEvent : ViewEvent {
    data class OnChangeCommunityMyHistory(val checked: Boolean) : HomeViewEvent
    object OnClickEditFloatingBtn : HomeViewEvent
}

data class HomeState(
    val isOnMyHistory: Boolean = false,
    val weatherSummary: HomeWeatherSummary? = FakeHomeWeatherSummary.getFakeModel(),
    val communityContentList: List<CommunityContent> = FakeCommunityContent.getFakeModelList()
) : ViewState {
}