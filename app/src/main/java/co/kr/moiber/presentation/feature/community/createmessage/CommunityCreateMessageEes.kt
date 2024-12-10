package co.kr.moiber.presentation.feature.community.createmessage

import co.kr.moiber.R
import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import co.kr.moiber.shared.components.model.ModalState

sealed interface CommunityCreateMessageDialogTag {
    data object CreateMessageBackPress : CommunityCreateMessageDialogTag
}

sealed interface CommunityCreateMessageViewEvent : ViewEvent {
    /** Step1 */
    data class OnSelectUpperWear(val upperWear: UpperWear?) : CommunityCreateMessageViewEvent
    data class OnSelectBottomWear(val bottomWear: BottomWear?) : CommunityCreateMessageViewEvent
    data class OnSelectOuterWear(val outerWear: OuterWear?) : CommunityCreateMessageViewEvent
    data object OnClickStep1NextBtn : CommunityCreateMessageViewEvent

    /** Step2 */
    data class OnChangeTemperature(val temperature: Int) : CommunityCreateMessageViewEvent
    data object OnClickStep2PreviousBtn : CommunityCreateMessageViewEvent
    data object OnClickStep2NextBtn : CommunityCreateMessageViewEvent

    /** Step3 */
    data class OnChangeMessage(val message: String?) : CommunityCreateMessageViewEvent
    data object OnClickStep3PreviousBtn : CommunityCreateMessageViewEvent
    data object OnClickStep3CompleteBtn : CommunityCreateMessageViewEvent

    /** modal */
    data object OnCloseDialog : CommunityCreateMessageViewEvent
    data object OnClickBackPressDialogFinish : CommunityCreateMessageViewEvent

    /** else */
    data object OnBackPressed : CommunityCreateMessageViewEvent
}

sealed interface CommunityCreateMessageSideEffect : SideEffect {
    data object ScrollToNextPage : CommunityCreateMessageSideEffect
    data object ScrollToPreviousPage : CommunityCreateMessageSideEffect
    data object PopBackStackWithSuccess : CommunityCreateMessageSideEffect
    data object PopBackStack : CommunityCreateMessageSideEffect
}

data class CreateMessageState(
    val isModify: Boolean = false,
    val messageId: Long? = null,
    /** step1 */
    val upperWear: UpperWear? = null,
    val bottomWear: BottomWear? = null,
    val outerWear: OuterWear? = null,
    val step1ErrorMsg: String? = null,
    /** step2 */
    val temperature: Int = 0,
    val step2Error: Boolean = false,
    /** step3 */
    val message: String? = null,
    val step3Error: Boolean = false,
    /** modal */
    val dialogState: ModalState<CommunityCreateMessageDialogTag> =
        ModalState.Closed(CommunityCreateMessageDialogTag.CreateMessageBackPress)
) : ViewState {

    /** step1 */
    val isStep1NextBtnEnable: Boolean
        get() = upperWear != null && bottomWear != null

    /** step2 */
    val tempText: String
        get() = when (temperature) {
            0 -> "온도계를 조절해보세요!"
            1 -> "ㄷㄷㄷ 넘 추워요"
            2 -> "살짝 쌀쌀해요"
            3 -> "딱 좋아!"
            4 -> "약간 후덥지근해요"
            else -> "헥헥 너무 더워요"
        }

    val tempImg: Int
        get() = when (temperature) {
            0 -> R.drawable.img_empty
            1 -> R.drawable.img_cold_l
            2 -> R.drawable.img_cool_l
            3 -> R.drawable.img_good_l
            4 -> R.drawable.img_muggy_l
            else -> R.drawable.img_hot_l
        }
    val isStep2NextBtnEnable: Boolean
        get() = temperature != 0
}