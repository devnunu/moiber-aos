package co.kr.moiber.presentation.home.community.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.blue01
import co.kr.moiber.shared.ui.white01

@Composable
fun MoiberSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    width: Dp = 32.dp,
    height: Dp = 16.dp,
    gapBetweenThumbAndTrackEdge: Dp = 3.dp
) {

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    val animatePosition = animateFloatAsState(
        targetValue = if (checked)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() },
        label = ""
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onCheckedChange(!checked)
                    }
                )
            }
    ) {

        drawRoundRect(
            color = if (checked) black02 else black02.copy(alpha = 0.5f),
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
        )

        drawCircle(
            color = white01,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoiberSwitchPreview() {
    MoiberSwitch(
        checked = true,
        onCheckedChange = {},
    )
}