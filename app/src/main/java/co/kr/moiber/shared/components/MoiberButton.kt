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

enum class ButtonSize {
    SMALL,
    LARGE
}

@Composable
fun MoiberButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(4.dp),
    backgroundColor: Color,
    fontColor: Color,
    fontStyle: TextStyle,
    buttonSize: ButtonSize,
    text: String,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
    ) {
        val padding = if (buttonSize == ButtonSize.LARGE) 16.5.dp else 6.dp
        Row(
            modifier = Modifier.padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                style = fontStyle,
                color = fontColor,
                text = text,
                textAlign = TextAlign.Center,
            )
        }
    }
}