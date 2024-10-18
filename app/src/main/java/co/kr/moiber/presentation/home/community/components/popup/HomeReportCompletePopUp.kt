package co.kr.moiber.presentation.home.community.components.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.white01

@Composable
fun HomeReportCompletePopUp(
    onEvent: (HomeCommunityViewEvent) -> Unit
) {
    MoiberPopUp(
        horizontalPadding = 40.dp,
        onDismissRequest = { onEvent(HomeCommunityViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_report),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                style = Body04,
                text = "신고가 완료되었어요.",
                color = black02
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                style = Body09,
                text = "신고된 내용은 운영 정책에 따라 \n검토 후 필요한 조치가 진행될 예정이에요.",
                textAlign = TextAlign.Center,
                color = black02
            )
            Spacer(modifier = Modifier.height(18.dp))
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = black02,
                fontColor = white01,
                fontStyle = Body07,
                buttonSize = ButtonSize.MEDIUM,
                onClick = { onEvent(HomeCommunityViewEvent.OnCloseDialog) },
                text = "확인"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeReportCompletePopUpPreview() {
    HomeReportCompletePopUp(
        onEvent = {}
    )
}