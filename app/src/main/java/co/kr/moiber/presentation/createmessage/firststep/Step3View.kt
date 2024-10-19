package co.kr.moiber.presentation.createmessage.firststep

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.createmessage.CreateMessageState
import co.kr.moiber.presentation.createmessage.CreateMessageViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.input.MoiberTextField
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.red01
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01
import co.kr.moiber.shared.ui.yellow02
import co.kr.moiber.shared.ui.yellow03

@Composable
fun Step3View(
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
            text = "오늘 날씨에 대해\n한마디 적어보자면?",
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(41.dp))
        Image(
            painter = painterResource(id = state.tempImg),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(41.dp))
        MoiberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            padding = 16.dp,
            radius = 12.dp,
            borderColor = yellow02,
            backgroundColor = yellow03,
            value = state.message.orEmpty(),
            placeHolder = "날씨 한마디는 선택사항이에요.",
            onValueChange = { onEvent(CreateMessageViewEvent.OnChangeMessage(it)) }
        )
        if(state.step3Error) {
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = "※ 최대 45자까지 입력 가능해요",
                color = red01
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = yellow01,
                fontColor = white01,
                fontStyle = Body04,
                buttonSize = ButtonSize.LARGE,
                shape = RoundedCornerShape(100.dp),
                text = "게시하기",
                onClick = { onEvent(CreateMessageViewEvent.OnClickStep3CompleteBtn) }
            )
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = white01,
                fontColor = gray01,
                fontStyle = Body04,
                buttonSize = ButtonSize.LARGE,
                text = "이전으로",
                onClick = { onEvent(CreateMessageViewEvent.OnClickStep2PreviousBtn) }
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun Step3ViewPreview() {
    Step3View(
        state = CreateMessageState(),
        onEvent = {}
    )
}