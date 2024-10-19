package co.kr.moiber.presentation.createmessage.components.divider

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02

@Composable
fun SliderStepDivider(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    radius: Dp = 10.dp
) {
    // Animate color, width, and thickness based on isSelected
    val animatedColor by animateColorAsState(targetValue = if (isSelected) gray01 else gray02)
    val animatedWidth by animateDpAsState(targetValue = if (isSelected) 14.dp else 12.dp)
    val animatedThickness by animateDpAsState(targetValue = if (isSelected) 4.dp else 2.dp)

    Canvas(
        modifier = modifier
            .width(animatedWidth)
            .height(animatedThickness)
    ) {
        drawRoundRect(
            color = animatedColor,
            size = size,
            cornerRadius = CornerRadius(radius.toPx(), radius.toPx())
        )
    }
}