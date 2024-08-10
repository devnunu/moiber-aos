package co.kr.moiber.model

import androidx.annotation.DrawableRes
import co.kr.moiber.R

data class WeatherMessage(
    val type: WeatherMessageType? = null,
    val message: String? = null
)

enum class WeatherMessageType(
    @DrawableRes val iconResId: Int? = null
) {
    NORMAL(null),     // 일반
    WARNING(R.drawable.icn_warning1),     // 주의
    CAUTION(R.drawable.icn_caution),    // 경고
}