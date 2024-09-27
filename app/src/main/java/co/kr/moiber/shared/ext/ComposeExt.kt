package co.kr.moiber.shared.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

private const val DEFAULT_THROTTLE_DURATION = 300L

fun Modifier.fadingEdge(): Modifier = this.then(
    Modifier
        .graphicsLayer { alpha = 0.99F }
        .drawWithContent {
            drawContent()
            val bottomColors = listOf(Color.Black, Color.Transparent)
            val bottomEndY = size.height
            val bottomGradientHeight = 93.dp.toPx()
            drawRect(
                brush = Brush.verticalGradient(
                    colors = bottomColors,
                    startY = bottomEndY - bottomGradientHeight,
                    endY = bottomEndY
                ),
                blendMode = BlendMode.DstIn
            )
        }
)

fun Modifier.clickableRipple(
    bounded: Boolean = false,
    throttleDuration: Long = DEFAULT_THROTTLE_DURATION,
    onClick: () -> Unit,
): Modifier = composed {
    var lastEventMilli by remember { mutableStateOf(0L) }
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(bounded = bounded),
        onClick = {
            val now = System.currentTimeMillis()
            if (now - lastEventMilli >= throttleDuration) {
                lastEventMilli = now
                onClick()
            }
        }
    )
}

fun Modifier.clickableNonIndication(
    duration: Long = DEFAULT_THROTTLE_DURATION,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: () -> Unit
): Modifier = composed {
    var lastEventMilli by remember { mutableStateOf(0L) }
    this.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = {
            val now = System.currentTimeMillis()
            if (now - lastEventMilli >= duration) {
                lastEventMilli = now
                onClick()
            }
        }
    )
}