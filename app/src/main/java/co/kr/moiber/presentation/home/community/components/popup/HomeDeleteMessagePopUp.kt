package co.kr.moiber.presentation.home.community.components.popup

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
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
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
fun HomeDeleteMessagePopUp(
    message: CommunityMessage,
    onEvent: (HomeCommunityViewEvent) -> Unit
) {
    MoiberPopUp(
        horizontalPadding = 40.dp,
        onDismissRequest = { onEvent(HomeCommunityViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 30.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = Body04,
                text = "내 게시글을 삭제할까요?",
                color = black01
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                style = Body08,
                text = "삭제된 게시글은 복구 할 수 없어요.",
                color = black02
            )
            Spacer(modifier = Modifier.size(25.dp))
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = red01,
                fontColor = white01,
                fontStyle = Body07,
                buttonSize = ButtonSize.MEDIUM,
                text = "네, 삭제할게요",
                onClick = { onEvent(HomeCommunityViewEvent.OnClickDialogFinalDeleteBtn(message)) }
            )
            Spacer(modifier = Modifier.size(5.dp))
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = white01,
                fontColor = red01,
                fontStyle = Body07,
                buttonSize = ButtonSize.MEDIUM,
                text = "아니요, 괜찮아요",
                onClick = { onEvent(HomeCommunityViewEvent.OnCloseDialog) }
            )
        }
    }
}