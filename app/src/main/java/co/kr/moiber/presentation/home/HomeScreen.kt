package co.kr.moiber.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import co.kr.moiber.presentation.home.summary.HomeSummaryScreen
import co.kr.moiber.presentation.home.summary.HomeSummaryViewModel

@Composable
fun HomeScreen(
    summaryViewModel: HomeSummaryViewModel = hiltViewModel()
) {
    HomeSummaryScreen(
        state = summaryViewModel.stateFlow.collectAsState().value,
        onEvent = summaryViewModel::onEvent
    )
}
