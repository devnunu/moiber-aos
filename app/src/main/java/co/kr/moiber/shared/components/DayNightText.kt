package co.kr.moiber.shared.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import co.kr.moiber.shared.ui.black
import co.kr.moiber.shared.ui.white01

@Composable
fun DayNightText(
    style: TextStyle,
    isDay: Boolean,
    text: String
) {
    val color = if (isDay) black else white01
    Text(
        style = style,
        text = text,
        color = color
    )
}