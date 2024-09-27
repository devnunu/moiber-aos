package co.kr.moiber.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.kr.moiber.presentation.home.community.HomeCommunityScreen
import co.kr.moiber.presentation.home.components.header.TopHeaderView
import co.kr.moiber.presentation.home.components.indicator.PageIndicator
import co.kr.moiber.presentation.home.summary.HomeSummaryScreen
import co.kr.moiber.presentation.home.summary.components.animation.HomeAnimationVisibility
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.yellow03
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen(
    state: HomeState,
    onEvent: (HomeViewEvent) -> Unit
) {
    val numberOfPage = 2
    var isVisible by remember { mutableStateOf(false) }
    val bgColor = if (state.weatherSummary?.isDay == true) yellow03 else black02
    val pagerState = rememberPagerState(pageCount = { numberOfPage })

    LaunchedEffect(Unit) {
        delay(150)
        isVisible = true
    }
    SetStatusBarColor(color = bgColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeAnimationVisibility(
            visible = isVisible,
            duration = 400,
            delay = 150
        ) {
            TopHeaderView(
                isDay = state.weatherSummary?.isDay ?: true
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { index ->
                when (index) {
                    0 -> {
                        HomeSummaryScreen(
                            isVisible = isVisible,
                            state = state,
                            onEvent = onEvent
                        )
                    }

                    1 -> {
                        HomeCommunityScreen(
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }
            }
            PageIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 41.dp),
                numberOfPages = numberOfPage,
                selectedPage = pagerState.currentPage,
            )
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}
