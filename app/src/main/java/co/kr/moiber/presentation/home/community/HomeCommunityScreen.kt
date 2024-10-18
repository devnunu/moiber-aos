package co.kr.moiber.presentation.home.community

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.presentation.home.community.components.CommunityHeader
import co.kr.moiber.presentation.home.community.components.message.MessageItem
import co.kr.moiber.presentation.home.community.components.EditFloatingButton
import co.kr.moiber.presentation.home.community.components.popup.HomeLongPressPopUp
import co.kr.moiber.presentation.home.community.components.popup.HomeReportCompletePopUp
import co.kr.moiber.presentation.home.community.components.popup.HomeReportPopUp
import co.kr.moiber.presentation.home.components.weather.WeatherContent
import co.kr.moiber.presentation.navigation.NavRoute
import co.kr.moiber.shared.components.bottomsheet.BottomSheetWrapper
import co.kr.moiber.shared.components.popup.PopUpWrapper
import co.kr.moiber.shared.ext.fadingEdge

@Composable
fun HomeCommunityScreen(
    viewModel: HomeCommunityViewModel = hiltViewModel(),
    navController: NavController
) {
    HomeCommunityScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent,
        navController = navController
    )
}

@Composable
private fun HomeCommunityScreen(
    state: HomeCommunityState,
    onEvent: (HomeCommunityViewEvent) -> Unit,
    navController: NavController
) {
    val isDay = state.isDay

    PopUpWrapper(dialogState = state.dialogState) { tag ->
        when (tag) {
            is HomeCommunityDialogTag.LongPress -> {
                HomeLongPressPopUp(
                    message = tag.message,
                    onEvent = onEvent
                )
            }

            is HomeCommunityDialogTag.Report -> {
                HomeReportPopUp(
                    state = state,
                    onEvent = onEvent
                )
            }

            is HomeCommunityDialogTag.ReportComplete -> {
                HomeReportCompletePopUp(
                    onEvent = onEvent
                )
            }
        }
    }
    BottomSheetWrapper(
        viewModelSheetState = state.bottomSheetState,
        onCloseBottomSheet = { onEvent(HomeCommunityViewEvent.OnCloseBottomSheet) }
    ) {
        when (state.bottomSheetState.tag) {
            is HomeCommunityBottomSheetTag.WeatherDetail -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    WeatherContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 28.dp, end = 28.dp, bottom = 30.dp),
                        weatherSummary = FakeHomeWeatherSummary.getFakeModel()
                    )
                }
            }
        }
    }
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            ) {
                CommunityHeader(
                    modifier = Modifier.padding(vertical = 7.dp),
                    isDay = isDay,
                    checked = state.isOnMyHistory,
                    onCheckedChange = { checked ->
                        onEvent(HomeCommunityViewEvent.OnChangeCommunityMyHistory(checked))
                    }
                )
                Spacer(modifier = Modifier.size(12.dp))
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fadingEdge()
                    .padding(horizontal = 18.dp)
            ) {
                val filteredContentList =
                    if (state.isOnMyHistory) state.communityMessageList.filter {
                        it.isMyContent(userId = 0)
                    }
                    else state.communityMessageList
                items(filteredContentList) { communityMessage ->
                    MessageItem(
                        isDay = isDay,
                        communityMessage = communityMessage,
                        onClickMyVanMessage = { navController.navigate(NavRoute.Report) },
                        onClickMessage = { message ->
                            onEvent(HomeCommunityViewEvent.OnClickMessageItem(message))
                        },
                        onLongClickMessage = { message ->
                            onEvent(HomeCommunityViewEvent.OnLongClickMessageItem(message))
                        }
                    )
                    val space = if (communityMessage.like != null) 10.dp else 20.dp
                    Spacer(modifier = Modifier.size(space))
                }
                item {
                    Spacer(modifier = Modifier.size(40.dp))
                }
            }
        }
        EditFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 22.dp),
            onClick = {
                onEvent(HomeCommunityViewEvent.OnClickEditFloatingBtn)
            }
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFAEF
)
@Composable
fun HomeCommunityScreenPreview() {
    HomeCommunityScreen(
        state = HomeCommunityState(),
        onEvent = {},
        navController = rememberNavController()
    )
}