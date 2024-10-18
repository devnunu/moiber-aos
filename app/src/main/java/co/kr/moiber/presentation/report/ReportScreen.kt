package co.kr.moiber.presentation.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.input.MoiberTextField
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.components.popup.PopUpWrapper
import co.kr.moiber.shared.ext.clickableRipple
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body02
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body05
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.Body11
import co.kr.moiber.shared.ui.Title02
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01

@Composable
fun ReportScreen(
    viewModel: ReportViewModel = hiltViewModel(),
    navController: NavController
) {
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ReportSideEffect.PopBackStack -> {
                navController.popBackStack()
            }
        }
    }
    ReportScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ReportScreen(
    state: ReportState,
    onEvent: (ReportViewEvent) -> Unit
) {
    PopUpWrapper(dialogState = state.dialogState) { tag ->
        when (tag) {
            is ReportDialogTag.Complete -> {
                MoiberPopUp(
                    horizontalPadding = 40.dp,
                    onDismissRequest = { onEvent(ReportViewEvent.OnClickCompleteDialogBtn) }
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
                            text = "신고 철회 요청 완료",
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
                            onClick = { onEvent(ReportViewEvent.OnClickCompleteDialogBtn) },
                            text = "확인"
                        )
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp),
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.icn_close),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.icn_caution),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                style = Title03,
                text = "신고 철회 요청",
            )
        }
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            style = Body09,
            text = "타당하지 않은 이유로 신고 당한 경우 신고 철회를 요청해주세요.\n검토 후 사용자 님께 접수된 신고를 철회해 드릴 수 있어요.",
        )
        Spacer(modifier = Modifier.size(16.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = black02
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(gray02, RoundedCornerShape(8.dp))
                .padding(vertical = 12.dp, horizontal = 13.dp)
        ) {
            Text(
                style = Body07,
                text = "신고된 내 게시글"
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                style = Body09,
                text = "공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 공",
                color = black02
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = Body11,
                text = "2024.08.19 AM 08:21",
                color = gray01,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.size(23.dp))
        Column(
            modifier = Modifier.padding(start = 10.dp, end = 5.dp)
        ) {
            Text(
                style = Body08,
                text = "※ 신고 처리 방침",
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                style = Body11,
                text = "다수에게 신고 당한 게시글이 3개 이상일 시 3일 동안 커뮤니티 게시글 작성이 불가능합니다. \n" +
                        "\n" +
                        "다수에게 신고 당한 게시글이 5개 이상일 시 7일 동안 커뮤니티 게시글 작성이 불가능합니다.  \n" +
                        "\n" +
                        "다수에게 신고 당한 게시글이 10개 이상일 시 모이버의 서비스 이용이 \n" +
                        "불가능합니다. ",
                color = gray01
            )
        }
        Spacer(modifier = Modifier.size(25.dp))
        val value = state.reportTxt
        MoiberTextField(
            height = 124.dp,
            value = value.orEmpty(),
            placeHolder = "직접 입력하기(공백 포함 최대 150자)",
            onValueChange = { text -> onEvent(ReportViewEvent.OnChangeReportTxt(text)) }
        )
        Spacer(modifier = Modifier.size(18.dp))
        MoiberButton(
            modifier = Modifier
                .fillMaxWidth()
                .clickableRipple(onClick = {}, bounded = true),
            enable = state.isBottomCtaEnable,
            backgroundColor = black02,
            fontColor = white01,
            fontStyle = Body04,
            buttonSize = ButtonSize.LARGE,
            text = "신고 철회 요청하기",
            onClick = {
                onEvent(ReportViewEvent.OnClickCompleteBtn)
            }
        )
        Spacer(modifier = Modifier.size(18.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = Body11,
            text = "*신고의 이유가 정당한 경우 신고가 신고가 철회되지 않을 수 있어요.",
            color = gray01,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
    ReportScreen(
        state = ReportState(
        ),
        onEvent = {}
    )
}