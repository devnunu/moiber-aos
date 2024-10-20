package co.kr.moiber.presentation.createmessage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.presentation.createmessage.CreateMessageVariable.SUCCESS_MESSAGE_POST
import co.kr.moiber.presentation.createmessage.components.indicator.CreateMessageIndicator
import co.kr.moiber.presentation.createmessage.components.popup.CreateMessageBackPressPopUp
import co.kr.moiber.presentation.createmessage.firststep.Step1View
import co.kr.moiber.presentation.createmessage.firststep.Step2View
import co.kr.moiber.presentation.createmessage.firststep.Step3View
import co.kr.moiber.presentation.navigation.NavRoute
import co.kr.moiber.presentation.navigation.setResult
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.popup.PopUpWrapper
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.LaunchedEffectOnce
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

object CreateMessageVariable {
    const val NUMBER_OF_PAGE = 3
    const val SUCCESS_MESSAGE_POST = "successMessagePost"
}

@Composable
fun CreateMessageScreen(
    args: NavRoute.CreateMessage,
    navController: NavController,
    viewModel: CreateMessageViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { CreateMessageVariable.NUMBER_OF_PAGE })
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CreateMessageSideEffect.ScrollToNextPage -> {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }

            is CreateMessageSideEffect.ScrollToPreviousPage -> {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }

            is CreateMessageSideEffect.PopBackStackWithSuccess -> {
                navController.setResult(SUCCESS_MESSAGE_POST, true)
                navController.popBackStack()
            }

            is CreateMessageSideEffect.PopBackStack -> {
                navController.popBackStack()
            }
        }
    }
    LaunchedEffectOnce {
        if (args.communityMessage != null) {
            viewModel.setInitialStateWhenModify(args.communityMessage)
        }
    }
    CreateMessageScreen(
        pagerState = pagerState,
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CreateMessageScreen(
    pagerState: PagerState,
    state: CreateMessageState,
    onEvent: (CreateMessageViewEvent) -> Unit
) {
    var pageIndex by remember { mutableIntStateOf(0) }
    BackHandler {
        onEvent(CreateMessageViewEvent.OnBackPressed)
    }
    PopUpWrapper(dialogState = state.dialogState) { tag ->
        when (tag) {
            is CreateMessageDialogTag.CreateMessageBackPress -> {
                CreateMessageBackPressPopUp(
                    isModify = state.isModify,
                    onEvent = onEvent
                )
            }
        }
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged() // Only emit when the page changes
            .collectLatest { page ->
                pageIndex = page
            }
    }
    MoiberScaffold(
        bottomBar = {
            when (pageIndex) {
                0 -> {
                    MoiberButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 66.dp, start = 20.dp, end = 20.dp),
                        enable = state.isStep1NextBtnEnable,
                        backgroundColor = yellow01,
                        fontColor = white01,
                        fontStyle = Body04,
                        buttonSize = ButtonSize.LARGE,
                        shape = RoundedCornerShape(100.dp),
                        text = "다음",
                        onClick = { onEvent(CreateMessageViewEvent.OnClickStep1NextBtn) }
                    )
                }

                1 -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 66.dp, start = 20.dp, end = 20.dp),
                    ) {
                        MoiberButton(
                            modifier = Modifier.weight(1f),
                            backgroundColor = gray01,
                            fontColor = white01,
                            fontStyle = Body04,
                            buttonSize = ButtonSize.LARGE,
                            shape = RoundedCornerShape(100.dp),
                            text = "이전",
                            onClick = { onEvent(CreateMessageViewEvent.OnClickStep2PreviousBtn) }
                        )
                        Spacer(modifier = Modifier.size(24.dp))
                        MoiberButton(
                            modifier = Modifier.weight(1f),
                            enable = state.isStep2NextBtnEnable,
                            disableBackgroundColor = gray02,
                            backgroundColor = yellow01,
                            fontColor = white01,
                            fontStyle = Body04,
                            buttonSize = ButtonSize.LARGE,
                            shape = RoundedCornerShape(100.dp),
                            text = "다음",
                            onClick = { onEvent(CreateMessageViewEvent.OnClickStep2NextBtn) }
                        )
                    }
                }

                2 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                    ) {
                        MoiberButton(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = yellow01,
                            fontColor = white01,
                            fontStyle = Body04,
                            buttonSize = ButtonSize.LARGE,
                            shape = RoundedCornerShape(100.dp),
                            text = "게시하기",
                            onClick = { onEvent(CreateMessageViewEvent.OnClickStep3CompleteBtn) }
                        )
                        MoiberButton(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = white01,
                            fontColor = gray01,
                            fontStyle = Body04,
                            buttonSize = ButtonSize.LARGE,
                            text = "이전으로",
                            onClick = { onEvent(CreateMessageViewEvent.OnClickStep2PreviousBtn) }
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {
            Spacer(modifier = Modifier.size(26.dp))
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.icn_close),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
                CreateMessageIndicator(
                    pagerState = pagerState
                )
            }
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                state = pagerState,
                verticalAlignment = Alignment.Top,
                userScrollEnabled = false
            ) { index ->
                when (index) {
                    0 -> {
                        Step1View(
                            state = state,
                            onEvent = onEvent
                        )
                    }

                    1 -> {
                        Step2View(
                            state = state,
                            onEvent = onEvent
                        )
                    }

                    2 -> {
                        Step3View(
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }
            }
        }
    }
}