package co.kr.moiber.presentation.createmessage

import co.kr.moiber.R
import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState

sealed interface CreateMessageViewEvent : ViewEvent {
    /** Step1 */
    data class OnSelectUpperWear(val upperWear: UpperWear?) : CreateMessageViewEvent
    data class OnSelectBottomWear(val bottomWear: BottomWear?) : CreateMessageViewEvent
    data class OnSelectOuterWear(val outerWear: OuterWear?) : CreateMessageViewEvent
    data object OnClickStep1NextBtn : CreateMessageViewEvent

    /** Step2 */
    data class OnChangeTemperature(val temperature: Int) : CreateMessageViewEvent
    data object OnClickStep2PreviousBtn : CreateMessageViewEvent
    data object OnClickStep2NextBtn : CreateMessageViewEvent
}

sealed interface CreateMessageSideEffect : SideEffect {
    data object ScrollToNextPage : CreateMessageSideEffect
    data object ScrollToPreviousPage : CreateMessageSideEffect
}

data class CreateMessageState(
    /** step1 */
    val upperWear: UpperWear? = null,
    val bottomWear: BottomWear? = null,
    val outerWear: OuterWear? = null,
    val step1ErrorMsg: String? = null,
    /** step2 */
    val temperature: Int = 0,
    val step2Error: Boolean = false
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