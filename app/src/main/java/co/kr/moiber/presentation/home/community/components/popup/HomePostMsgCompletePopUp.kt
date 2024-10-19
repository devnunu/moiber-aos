package co.kr.moiber.presentation.home.community.components.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.black02

@Composable
fun HomePostMsgCompletePopUp(
    onEvent: (HomeCommunityViewEvent) -> Unit
) {
    MoiberPopUp(
        horizontalPadding = 50.dp,
        onDismissRequest = { onEvent(HomeCommunityViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                painter = painterResource(id = R.drawable.img_congraturation),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                style = Body04,
                text = "게시물 수정 완료!",
                color = black02
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                style = Body09,
                text = "오늘의 내 옷차림과 날씨가 기록되었어요.",
                color = black02
            )
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}