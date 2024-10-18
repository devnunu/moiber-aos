package co.kr.moiber.presentation.home.community.components.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.CommunityLike
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ext.clickableRipple
import co.kr.moiber.shared.ui.Body05

@Composable
fun HomeLongPressPopUp(
    message: CommunityMessage,
    onEvent: (HomeCommunityViewEvent) -> Unit
) {
    val isMyLike = message.like?.isMyLike ?: false
    val hasMessage = !message.text.isNullOrBlank()
    MoiberPopUp(
        horizontalPadding = 50.dp,
        onDismissRequest = { onEvent(HomeCommunityViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LongPressTextWithIcon(
                drawableResId = R.drawable.icn_heart,
                text = if (isMyLike) "공감 취소" else "공감하기",
                onClick = { onEvent(HomeCommunityViewEvent.OnClickDialogLikeBtn(message)) }
            )
            if (hasMessage) {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFF3F3F3)
                )
                LongPressTextWithIcon(
                    drawableResId = R.drawable.icn_report,
                    text = "신고하기",
                    onClick = { onEvent(HomeCommunityViewEvent.OnClickDialogReportBtn) }
                )
            }
        }
    }
}

@Composable
fun LongPressTextWithIcon(
    drawableResId: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableRipple(bounded = true) { onClick() }
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = drawableResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            style = Body05,
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLongPressPopUpPreview1() {
    HomeLongPressPopUp(
        message = CommunityMessage(id = 0, userId = 0),
        onEvent = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeLongPressPopUpPreview2() {
    HomeLongPressPopUp(
        message = CommunityMessage(
            id = 0,
            userId = 0,
            text = "123",
            like = CommunityLike(count = 1, isMyLike = true)
        ),
        onEvent = {}
    )
}