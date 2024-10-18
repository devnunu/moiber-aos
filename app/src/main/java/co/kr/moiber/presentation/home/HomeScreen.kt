package co.kr.moiber.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.presentation.home.HomeVariable.NUMBER_OF_PAGE
import co.kr.moiber.presentation.home.community.HomeCommunityScreen
import co.kr.moiber.presentation.home.community.HomeCommunityViewModel
import co.kr.moiber.presentation.home.components.header.TopHeaderView
import co.kr.moiber.presentation.home.components.indicator.PageIndicator
import co.kr.moiber.presentation.home.summary.HomeSummaryScreen
import co.kr.moiber.presentation.home.summary.HomeSummaryViewModel
import co.kr.moiber.presentation.home.summary.components.animation.HomeAnimationVisibility
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.LaunchedEffectOnce
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.yellow03
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

object HomeVariable {
    const val NUMBER_OF_PAGE = 2
}

@Composable
fun HomeScreen(
    navController: NavController,
    homeSummaryViewModel: HomeSummaryViewModel = hiltViewModel(),
    homeCommunityViewModel: HomeCommunityViewModel = hiltViewModel()
) {
    var isDay by remember { mutableStateOf(true) }

    var isVisible by rememberSaveable { mutableStateOf(false) }
    val bgColor = if (isDay) yellow03 else black02
    val pagerState = rememberPagerState(pageCount = { NUMBER_OF_PAGE })

    LaunchedEffectOnce {
        delay(150)
        isVisible = true
    }
    MoiberScaffold {
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
                    isDay = isDay
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
                                viewModel = homeSummaryViewModel
                            )
                        }

                        1 -> {
                            HomeCommunityScreen(
                                navController = navController,
                                viewModel = homeCommunityViewModel
                            )
                        }
                    }
                }
                PageIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 41.dp),
                    numberOfPages = NUMBER_OF_PAGE,
                    selectedPage = pagerState.currentPage,
                )
            }
        }
    }
}

