package co.kr.moiber.presentation.createmessage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import co.kr.moiber.shared.components.popup.PopUpWrapper
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.LaunchedEffectOnce
import co.kr.moiber.shared.ext.collectSideEffect

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
    MoiberScaffold {
        Column {
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