package co.kr.moiber.presentation.feature.mypage.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.presentation.feature.mypage.MyPageState
import co.kr.moiber.presentation.feature.mypage.MyPageViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.input.MoiberInput
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01

@Composable
fun MyPageNickNameDialog(
    state: MyPageState,
    onEvent: (MyPageViewEvent) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    MoiberPopUp(
        horizontalPadding = 40.dp,
        onDismissRequest = { onEvent(MyPageViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp, top = 29.dp, bottom = 43.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    modifier = Modifier.clickableNonIndication { onEvent(MyPageViewEvent.OnCloseDialog) },
                    painter = painterResource(R.drawable.icn_close),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                style = Body04,
                text = "새로운 닉네임을 입력해주세요"
            )
            Spacer(modifier = Modifier.height(39.dp))
            MoiberInput(
                modifier = Modifier.width(242.dp),
                value = state.newNickName,
                placeholder = state.nickName.orEmpty(),
                focusRequester = focusRequester,
                onValueChange = { nickName ->
                    onEvent(MyPageViewEvent.OnChangeNickName(nickName))
                }
            )
            Spacer(modifier = Modifier.height(53.dp))
            MoiberButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = yellow01,
                fontColor = white01,
                fontStyle = Body07,
                buttonSize = ButtonSize.MEDIUM,
                text = "변경할게요",
                onClick = { onEvent(MyPageViewEvent.OnClickDialogChangeNickName) }
            )
        }
    }
}