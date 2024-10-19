package co.kr.moiber.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ext.clickableRipple
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01

enum class ButtonSize {
    SMALL,
    MEDIUM,
    LARGE
}

@Composable
fun MoiberButton(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color,
    disableBackgroundColor: Color = gray01,
    fontColor: Color,
    fontStyle: TextStyle,
    buttonSize: ButtonSize,
    onClick: () -> Unit,
    text: String,
) {
    val bgColor = if (enable) backgroundColor else disableBackgroundColor
    val textColor = if (enable) fontColor else white01
    Surface(
        modifier = modifier,
        shape = shape,
        color = bgColor,
    ) {
        val padding = when (buttonSize) {
            ButtonSize.LARGE -> 16.5.dp
            ButtonSize.MEDIUM -> 9.dp
            else -> 6.dp
        }
        Row(
            modifier = Modifier
                .clickableRipple(
                    onClick = onClick,
                    bounded = true
                )
                .padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                style = fontStyle,
                color = textColor,
                text = text,
                textAlign = TextAlign.Center,
            )
        }
    }
}