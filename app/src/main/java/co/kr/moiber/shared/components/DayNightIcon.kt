package co.kr.moiber.shared.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import co.kr.moiber.shared.ui.black
import co.kr.moiber.shared.ui.white01

@Composable
fun DayNightIcon(
    @DrawableRes iconResId: Int,
    isDay: Boolean
) {
    val color = if (isDay) black else white01
    Icon(
        painter = painterResource(id = iconResId),
        contentDescription = null,
        tint = color
    )
}