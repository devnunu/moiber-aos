package co.kr.moiber.presentation.createmessage.components.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.presentation.createmessage.CreateMessageState

@Composable
fun TemperatureSlider(
    modifier: Modifier = Modifier,
    state: CreateMessageState,
    onChangedValue: (Int) -> Unit
) {
    val temperature = state.temperature
    Box(
        modifier = modifier
    ) {
        VerticalSlider(
            modifier = Modifier
                .padding(top = 44.dp)
                .align(Alignment.Center),
            value = temperature,
            onChangedValue = onChangedValue
        )
        Image(
            painter = painterResource(id = state.tempImg),
            contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun TemperatureSliderPreview() {
    TemperatureSlider(
        state = CreateMessageState(temperature = 6),
        onChangedValue = {}
    )
}