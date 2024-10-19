package co.kr.moiber.presentation.createmessage.components.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.yellow01
import co.kr.moiber.shared.ui.yellow03
import kotlin.math.roundToInt


@Composable
fun VerticalSlider(
    modifier: Modifier = Modifier,
    value: Int,
    steps: Int = 5,
    trackWidth: Float = 20f,
    activeTrackColor: Color = yellow01,
    inactiveTrackColor: Color = yellow03,
    onChangedValue: (value: Int) -> Unit // Listener to notify the current step){}
) {
    // Calculate slider height in px
    val sliderHeightDp = 336.dp
    val sliderHeightPx = with(LocalDensity.current) { sliderHeightDp.toPx() }

    // Calculate step height based on steps
    val stepHeight = sliderHeightPx / steps

    val sliderPosition = value * stepHeight

    // Function to snap the position to nearest step and notify listener
    fun snapToStep(position: Float) {
        val step = (position / stepHeight).roundToInt().coerceIn(0, steps)
        onChangedValue(step) // Notify the listener with the current step
    }

    Box(
        modifier = modifier
            .width(trackWidth.dp)
            .height(sliderHeightDp)
            .background(Color.Transparent)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    val newPosition = (sliderPosition - delta).coerceIn(0f, sliderHeightPx)
                    snapToStep(newPosition)
                }
            )
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val tappedPosition = (sliderHeightPx - offset.y).coerceIn(0f, sliderHeightPx)
                    snapToStep(tappedPosition)
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val activeHeight = sliderPosition
            val inactiveHeight = size.height - activeHeight
            val trackWidthPx = trackWidth.dp.toPx()

            // Draw the inactive part of the track (full track)
            drawRoundRect(
                color = inactiveTrackColor,
                topLeft = Offset(0f, 0f),
                size = Size(trackWidthPx, size.height),
                cornerRadius = CornerRadius(trackWidthPx / 2, trackWidthPx / 2)
            )

            // Draw the active part of the track (overlaying the inactive track)
            drawRoundRect(
                color = activeTrackColor,
                topLeft = Offset(0f, inactiveHeight),
                size = Size(trackWidthPx, activeHeight),
                cornerRadius = CornerRadius(trackWidthPx / 2, trackWidthPx / 2)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVerticalSlider() {
    VerticalSlider(
        value = 1,
        onChangedValue = {}
    )
}