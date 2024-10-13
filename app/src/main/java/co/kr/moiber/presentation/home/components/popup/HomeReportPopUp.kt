package co.kr.moiber.presentation.home.components.popup

import android.widget.RadioGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.home.HomeState
import co.kr.moiber.presentation.home.HomeViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body10
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
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
    state: HomeState,
    onEvent: (HomeViewEvent) -> Unit
) {
    MoiberPopUp(
        onDismissRequest = { onEvent(HomeViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                style = Body03,
                text = "게시글을 신고하시나요?",
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            ) {
                ReportReason.entries.forEach {
                    Row(
                        modifier = Modifier.clickableNonIndication {
                            onEvent(HomeViewEvent.OnSelectReportReason(it))
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = state.selectedReportReason == it,
                            onClick = { onEvent(HomeViewEvent.OnSelectReportReason(it)) }
                        )
                        Text(
                            style = Body07,
                            text = it.reason
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(26.dp))
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = red01,
                fontColor = white01,
                fontStyle = Body06,
                buttonSize = ButtonSize.LARGE,
                onClick = { onEvent(HomeViewEvent.OnClickDialogCompleteReportBtn("")) },
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
    }
}