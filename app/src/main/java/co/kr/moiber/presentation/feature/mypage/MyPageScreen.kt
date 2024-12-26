package co.kr.moiber.presentation.feature.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.presentation.feature.mypage.components.button.MyPageSwitchButton
import co.kr.moiber.presentation.feature.mypage.components.button.MyPageTextButton
import co.kr.moiber.presentation.feature.mypage.components.dialog.MyPageNickNameDialog
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.popup.PopUpWrapper
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ui.Body01
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01

@Composable
fun MyPageScreen(
    navController: NavController,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    MyPageScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun MyPageScreen(
    state: MyPageState,
    onEvent: (MyPageViewEvent) -> Unit
) {

    PopUpWrapper(dialogState = state.dialogState) { tag ->
        when (tag) {
            is MyPageDialogTag.NickName -> {
                MyPageNickNameDialog(
                    state = state,
                    onEvent = onEvent
                )
            }
        }
    }
    MoiberScaffold {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.icn_back),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(107.dp))
                Text(
                    style = Body04,
                    text = "마이페이지"
                )
            }
            Spacer(modifier = Modifier.height(31.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp, horizontal = 22.dp)
            ) {
                Column {
                    Text(
                        style = Body01,
                        text = state.nickName.orEmpty(),
                        color = black02
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        style = Body09,
                        text = state.email.orEmpty(),
                        color = black02
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                MoiberButton(
                    backgroundColor = yellow01,
                    fontColor = white01,
                    fontStyle = Body07,
                    buttonSize = ButtonSize.MEDIUM,
                    shape = RoundedCornerShape(30.dp),
                    text = "변경",
                    onClick = { onEvent(MyPageViewEvent.OnClickChangeNickName) }
                )
            }
            Divider(thickness = 1.dp, color = gray02)
            MyPageSwitchButton(
                text = "알림 설정",
                checked = state.isGrantAlarmPermission,
                onCheckChange = { checked -> }
            )
            Divider(thickness = 1.dp, color = gray02)
            MyPageSwitchButton(
                text = "현재 위치 권한",
                checked = state.isGrantLocationPermission,
                onCheckChange = { checked -> }
            )
            Divider(thickness = 1.dp, color = gray02)
            MyPageTextButton(
                text = "약관 및 정책",
                onClick = {}
            )
            Divider(thickness = 1.dp, color = gray02)
            MyPageTextButton(
                text = "로그아웃",
                onClick = {}
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = Body09,
                    text = "탈퇴하기",
                    color = gray01
                )
            }
            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}