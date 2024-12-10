package co.kr.moiber.presentation.feature.intro.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import co.kr.moiber.presentation.navigation.NavRoute
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.yellow01
import co.kr.moiber.shared.ui.yellow02
import co.kr.moiber.R

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is WelcomeSideEffect.NavigateToMainHome -> {
                navController.navigate(NavRoute.Home)
            }
        }
    }
    WelcomeScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun WelcomeScreen(
    state: WelcomeState,
    onEvent: (WelcomeViewEvent) -> Unit
) {
    MoiberScaffold {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd),
                painter = painterResource(R.drawable.back_and_completed),
                contentDescription = null
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.size(132.dp))
                Text(
                    style = Title03,
                    text = "회원님,\n가입을 축하해요!",
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.size(20.dp))
                MoiberButton(
                    backgroundColor = yellow02,
                    fontColor = yellow01,
                    fontStyle = Body03,
                    buttonSize = ButtonSize.LARGE,
                    shape = RoundedCornerShape(100.dp),
                    text = "홈으로가기",
                    onClick = { onEvent(WelcomeViewEvent.OnClickGoToHomeBtn) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        state = WelcomeState(),
        onEvent = {}
    )
}