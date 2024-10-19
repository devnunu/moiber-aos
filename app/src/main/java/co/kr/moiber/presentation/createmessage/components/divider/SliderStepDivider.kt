package co.kr.moiber.presentation.createmessage.components.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
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
    val color = if (isSelected) gray01 else gray02
    val width = if (isSelected) 14.dp else 12.dp
    val thickness = if (isSelected) 4.dp else 2.dp
    Canvas(
        modifier = modifier
            .width(width)
            .height(thickness)
    ) {
        drawRoundRect(
            color = color,
            size = size,
            cornerRadius = CornerRadius(radius.toPx(), radius.toPx())
        )
    }
}