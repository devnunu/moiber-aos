package co.kr.moiber.shared.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.white01

@Composable
fun DayNightText(
    style: TextStyle,
    isDay: Boolean,
    text: String,
    dayColor: Color = black01,
    nightColor: Color = white01
) {
    val color = if (isDay) dayColor else nightColor
    Text(
        style = style,
        text = text,
        color = color
    )
}