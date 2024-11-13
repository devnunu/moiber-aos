package co.kr.moiber.presentation.feature.intro.nickname

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.presentation.navigation.NavRoute
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.input.MoiberInput
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01


@Composable
fun NickNameScreen(
    viewModel: NickNameViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NickNameSideEffect.NavigateToTerms -> {
                navController.navigate(NavRoute.Terms)
            }
        }
    }
    NickNameScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun NickNameScreen(
    state: NickNameState,
    onEvent: (NickNameViewEvent) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    MoiberScaffold(
        bottomBar = {
            MoiberButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 66.dp),
                shape = RoundedCornerShape(100.dp),
                backgroundColor = yellow01,
                fontColor = white01,
                fontStyle = Body04,
                buttonSize = ButtonSize.LARGE,
                enable = !state.nickName.isNullOrEmpty(),
                text = "다음",
                onClick = { onEvent(NickNameViewEvent.OnClickNextBtn) }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.icn_close),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(85.dp))
            Text(
                style = Title03,
                text = "반가워요!\n사용할 닉네임을 입력해주세요",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(97.dp))
            MoiberInput(
                modifier = Modifier.width(242.dp),
                value = state.nickName,
                placeholder = "입력하세요",
                focusRequester = focusRequester,
                onValueChange = { nickName ->
                    onEvent(NickNameViewEvent.OnChangeNickName(nickName))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NickNameScreenPreview() {
    NickNameScreen(
        state = NickNameState(),
        onEvent = {}
    )
}