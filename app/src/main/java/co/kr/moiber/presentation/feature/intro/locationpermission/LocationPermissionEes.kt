package co.kr.moiber.presentation.feature.intro.locationpermission

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface IntroLocationPermissionSideEffect : SideEffect {
    data object RequestPermission: IntroLocationPermissionSideEffect
}

sealed interface LocationPermissionViewEvent : ViewEvent {
    data object OnClickNextBtn : LocationPermissionViewEvent
}

data class LocationPermissionState(
    val temp: String? = null
) : ViewState {
}