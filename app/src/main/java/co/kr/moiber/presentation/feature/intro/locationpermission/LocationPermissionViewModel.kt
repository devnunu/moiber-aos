package co.kr.moiber.presentation.feature.intro.locationpermission

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LocationPermissionViewModel @Inject constructor() :
    BaseViewModel<LocationPermissionState, LocationPermissionViewEvent, LocationPermissionSideEffect>(
        initialState = LocationPermissionState()
    ) {
    override fun onEvent(event: LocationPermissionViewEvent) {
        when (event) {
            is LocationPermissionViewEvent.OnClickNextBtn -> {
                postSideEffect(LocationPermissionSideEffect.RequestPermission)
            }
        }
    }
}