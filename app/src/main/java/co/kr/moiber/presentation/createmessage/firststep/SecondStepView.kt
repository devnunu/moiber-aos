package co.kr.moiber.presentation.createmessage.firststep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.createmessage.CreateMessageState
import co.kr.moiber.presentation.createmessage.CreateMessageViewEvent
import co.kr.moiber.presentation.createmessage.components.divider.SliderStepDivider
import co.kr.moiber.presentation.createmessage.components.slider.TemperatureSlider
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01
import co.kr.moiber.shared.ui.yellow02

@Composable
fun SecondStepView(
    state: CreateMessageState,
    onEvent: (CreateMessageViewEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(38.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = Title03,
            text = "오늘 옷차림,\n날씨와 적합한가요?",
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(38.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .background(yellow02, RoundedCornerShape(20.dp))
                    .padding(horizontal = 26.dp, vertical = 10.dp),
                text = state.tempText,
                color = black02
            )
            TriangleShape()
        }
        Spacer(modifier = Modifier.size(8.dp))
        Box(Modifier.fillMaxWidth()) {
            TemperatureSlider(
                modifier = Modifier
                    .align(Alignment.Center),
                state = state,
                onChangedValue = { onEvent(CreateMessageViewEvent.OnChangeTemperature(it)) }
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(end = 40.dp, bottom = 10.dp)
            ) {
                SliderStepDivider(isSelected = state.temperature == 4)
                Spacer(modifier = Modifier.size(60.dp))
                SliderStepDivider(isSelected = state.temperature == 3)
                Spacer(modifier = Modifier.size(60.dp))
                SliderStepDivider(isSelected = state.temperature == 2)
                Spacer(modifier = Modifier.size(60.dp))
                SliderStepDivider(isSelected = state.temperature == 1)
                Spacer(modifier = Modifier.size(60.dp))
                SliderStepDivider(isSelected = state.temperature == 0)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 66.dp, start = 20.dp, end = 20.dp),
        ) {
            MoiberButton(
                modifier = Modifier.weight(1f),
                backgroundColor = gray01,
                fontColor = white01,
                fontStyle = Body04,
                buttonSize = ButtonSize.LARGE,
                shape = RoundedCornerShape(100.dp),
                text = "이전",
                onClick = { onEvent(CreateMessageViewEvent.OnClickStep2PreviousBtn) }
            )
            Spacer(modifier = Modifier.size(24.dp))
            MoiberButton(
                modifier = Modifier.weight(1f),
                enable = state.isStep2NextBtnEnable,
                disableBackgroundColor = gray02,
                backgroundColor = yellow01,
                fontColor = white01,
                fontStyle = Body04,
                buttonSize = ButtonSize.LARGE,
                shape = RoundedCornerShape(100.dp),
                text = "다음",
                onClick = { onEvent(CreateMessageViewEvent.OnClickStep2NextBtn) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondStepViewPreview() {
    SecondStepView(
        state = CreateMessageState(),
        onEvent = {}
    )
}

@Composable
fun TriangleShape() {
    Canvas(modifier = Modifier.size(23.dp, 12.dp)) {
        // Create a path for the downward triangle
        val path = Path().apply {
            // Move to the bottom center
            moveTo(size.width / 2, size.height)
            // Line to the top left
            lineTo(0f, 0f)
            // Line to the top right
            lineTo(size.width, 0f)
            // Close the path
            close()
        }
        // Draw the path with the specified color
        drawPath(
            path = path,
            color = yellow02
        )
    }
}