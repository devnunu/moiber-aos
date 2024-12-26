package co.kr.moiber.presentation.feature.mypage

import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import co.kr.moiber.shared.components.model.ModalState

sealed interface MyPageDialogTag {
    object NickName : MyPageDialogTag
}

sealed interface MyPageSideEffect : SideEffect {

}

sealed interface MyPageViewEvent : ViewEvent {
    data class OnCheckChangeAlarm(val checked: Boolean) : MyPageViewEvent
    data class OnCheckChangeLocation(val checked: Boolean) : MyPageViewEvent
    data object OnClickChangeNickName : MyPageViewEvent
    data object OnCloseDialog : MyPageViewEvent
    data class OnChangeNickName(val nickName: String) : MyPageViewEvent
    data object OnClickDialogChangeNickName : MyPageViewEvent
}

data class MyPageState(
    val nickName: String? = "쌀쌀부추전",
    val email: String? = "abc@kakao.com",
    val newNickName: String? = null,
    val isGrantAlarmPermission: Boolean = false,
    val isGrantLocationPermission: Boolean = false,
    val dialogState: ModalState<MyPageDialogTag> = ModalState.Closed(MyPageDialogTag.NickName)
) : ViewState {
}