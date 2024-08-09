package co.kr.moiber.model

import androidx.annotation.DrawableRes
import co.kr.moiber.R

enum class Weather(
    @DrawableRes
    val iconResId: Int
) {
    DAY_SUNNY(iconResId = R.drawable.icn_sun_l),      // 낮, 맑음
    DAY_CLOUDY(iconResId = R.drawable.icn_cloud1_l),     // 낮, 구름 조금
    NIGHT_SUNNY(iconResId = R.drawable.icn_moon_l),    // 밤, 맑음
    NIGHT_CLOUDY(iconResId = R.drawable.icn_cloud3_l),   // 밤, 구름 조금
    CLOUDY(iconResId = R.drawable.icn_cloud2_l),         // 구름 많음
    RAINY(iconResId = R.drawable.icn_rain_l),          // 비
    THUNDER(iconResId = R.drawable.icn_thunder_l),        // 번개
    SNOWY(iconResId = R.drawable.icn_snow_l)           // 눈
}