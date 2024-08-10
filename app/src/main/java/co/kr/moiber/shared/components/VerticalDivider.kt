package co.kr.moiber.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import co.kr.moiber.shared.ui.gray

@Composable
fun VerticalDivider(
    width: Dp,
    height: Dp,
    backgroundColor: Color = gray
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .background(backgroundColor)
    )
}