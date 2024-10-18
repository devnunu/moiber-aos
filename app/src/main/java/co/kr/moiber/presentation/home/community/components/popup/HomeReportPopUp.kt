package co.kr.moiber.presentation.home.community.components.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.presentation.home.community.HomeCommunityState
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.Body10
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.red01
import co.kr.moiber.shared.ui.white01

enum class ReportReason(val reason: String) {
    CASE1("개인 정보 노출, 유포"),
    CASE2("같은 내용 도배"),
    CASE3("광고 및 홍보성 내용"),
    CASE4("음란, 선정적 내용"),
    CASE5("불법 정보"),
    CASE6("욕설, 비방, 차별, 혐오"),
    CASE7("그 외 다른 사유"),
}

@Composable
fun HomeReportPopUp(
    state: HomeCommunityState,
    onEvent: (HomeCommunityViewEvent) -> Unit
) {
    MoiberPopUp(
        horizontalPadding = 30.dp,
        onDismissRequest = { onEvent(HomeCommunityViewEvent.OnCloseDialog) }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(30.dp))
                Text(
                    style = Body04,
                    text = "게시글을 신고하시나요?",
                )
                Spacer(modifier = Modifier.size(26.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp)
                ) {
                    ReportReason.entries.forEachIndexed { index, reportReason ->
                        if (index != 0) {
                            Spacer(modifier = Modifier.size(18.dp))
                        }
                        ReportSelectText(
                            selectedReportReasonList = state.selectedReportReasonList,
                            reportReason = reportReason,
                            onEvent = onEvent
                        )
                    }
                }
                Spacer(modifier = Modifier.size(26.dp))
                MoiberButton(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = red01,
                    fontColor = white01,
                    fontStyle = Body07,
                    buttonSize = ButtonSize.LARGE,
                    onClick = { onEvent(HomeCommunityViewEvent.OnClickDialogCompleteReportBtn("")) },
                    text = "신고하기"
                )
                Spacer(modifier = Modifier.size(13.dp))
                Text(
                    style = Body10,
                    text = "※ 타당하지 못한 신고 내용은 반영되지 않을 수 있어요.",
                    color = gray01
                )
                Spacer(modifier = Modifier.size(28.dp))
            }
            Icon(
                modifier = Modifier
                    .padding(top = 28.dp, end = 28.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clickable { onEvent(HomeCommunityViewEvent.OnCloseDialog) },
                painter = painterResource(id = R.drawable.icn_close),
                contentDescription = null,
                tint = black01
            )
        }
    }
}

@Composable
fun ReportSelectText(
    selectedReportReasonList: List<ReportReason>,
    reportReason: ReportReason,
    onEvent: (HomeCommunityViewEvent) -> Unit
) {
    Row(
        modifier = Modifier.clickableNonIndication {
            onEvent(HomeCommunityViewEvent.OnSelectReportReason(reportReason))
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checked = selectedReportReasonList.contains(reportReason)
        IconToggleButton(
            modifier = Modifier.size(18.dp),
            checked = checked,
            onCheckedChange = { onEvent(HomeCommunityViewEvent.OnSelectReportReason(reportReason)) }
        ) {
            Image(
                painter = painterResource(
                    if (checked) R.drawable.icn_check_on
                    else R.drawable.icn_check_off
                ),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            style = Body08,
            text = reportReason.reason
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeReportPopUpPreview() {
    HomeReportPopUp(
        state = HomeCommunityState(),
        onEvent = {}
    )
}