package co.kr.moiber.presentation.home

import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.HomeWeatherSummary
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed class HomeViewEvent : ViewEvent {

}

data class HomeState(
    val weatherSummary: HomeWeatherSummary? = FakeHomeWeatherSummary.getFakeModel(),
    val communityContentList: List<CommunityContent> = FakeCommunityContent.getFakeModelList()
) : ViewState {
}