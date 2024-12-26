package co.kr.moiber.presentation.feature.mypage

import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
) : BaseViewModel<MyPageState, MyPageViewEvent, MyPageSideEffect>(
    initialState = MyPageState()
) {
    override fun onEvent(event: MyPageViewEvent) {
        when (event) {
            is MyPageViewEvent.OnCheckChangeAlarm -> {
                setState { copy(isGrantAlarmPermission = event.checked) }
            }

            is MyPageViewEvent.OnCheckChangeLocation -> {
                setState { copy(isGrantLocationPermission = event.checked) }
            }

            is MyPageViewEvent.OnCloseDialog -> {
                setState { copy(dialogState = dialogState.close()) }
            }

            is MyPageViewEvent.OnClickChangeNickName -> {
                setState { copy(dialogState = dialogState.open(MyPageDialogTag.NickName)) }
            }

            is MyPageViewEvent.OnChangeNickName -> {
                setState { copy(newNickName = event.nickName) }
            }

            is MyPageViewEvent.OnClickDialogChangeNickName -> {
                setState {
                    copy(
                        newNickName = null,
                        nickName = newNickName,
                        dialogState = dialogState.close()
                    )
                }
            }

            else -> Unit
        }
    }
}