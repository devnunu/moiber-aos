package co.kr.moiber.presentation.createmessage.components.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.kr.moiber.presentation.createmessage.CreateMessageViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.red01
import co.kr.moiber.shared.ui.white01

@Composable
fun CreateMessageBackPressPopUp(
    isModify: Boolean,
    onEvent: (CreateMessageViewEvent) -> Unit
) {
    MoiberPopUp(
        horizontalPadding = 40.dp,
        onDismissRequest = { onEvent(CreateMessageViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 30.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                style = Body04,
                text = "다음에 작성하시겠어요?",
                color = black01
            )
            Spacer(modifier = Modifier.size(12.dp))
            val description = if (isModify) "지금까지 수정한 내용은 저장되지 않아요" else "지금까지 작성한 내용은 저장되지 않아요"
            Text(
                style = Body08,
                text = description,
                color = black02
            )
            Spacer(modifier = Modifier.size(25.dp))
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = red01,
                fontColor = white01,
                fontStyle = Body07,
                buttonSize = ButtonSize.MEDIUM,
                text = "네, 그만 쓸래요",
                onClick = { onEvent(CreateMessageViewEvent.OnClickBackPressDialogFinish) }
            )
            Spacer(modifier = Modifier.size(5.dp))
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = white01,
                fontColor = red01,
                fontStyle = Body07,
                buttonSize = ButtonSize.MEDIUM,
                text = "아니요, 계속 쓸게요",
                onClick = { onEvent(CreateMessageViewEvent.OnCloseDialog) }
            )
        }
    }
}