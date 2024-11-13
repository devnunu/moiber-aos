package co.kr.moiber.presentation.feature.intro.locationpermission

import co.kr.moiber.presentation.feature.intro.login.LoginSideEffect
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface LocationPermissionSideEffect : SideEffect {
    data object RequestPermission: LocationPermissionSideEffect
}

sealed interface LocationPermissionViewEvent : ViewEvent {
    data object OnClickNextBtn : LocationPermissionViewEvent
}

data class LocationPermissionState(
    val temp: String? = null
) : ViewState {
}