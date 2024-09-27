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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.home.HomeState
import co.kr.moiber.presentation.home.HomeViewEvent
import co.kr.moiber.presentation.home.community.components.CommunityHeader
import co.kr.moiber.presentation.home.community.components.message.MessageItem
import co.kr.moiber.presentation.home.community.components.EditFloatingButton
import co.kr.moiber.shared.ext.fadingEdge

@Composable
fun HomeCommunityScreen(
    state: HomeState,
    onEvent: (HomeViewEvent) -> Unit
) {
    val weatherSummary = state.weatherSummary
    val isDay = weatherSummary?.isDay ?: true
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
                        onEvent(HomeViewEvent.OnChangeCommunityMyHistory(checked))
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
                    if (state.isOnMyHistory) state.communityContentList.filter {
                        it.isMyContent(userId = 0)
                    }
                    else state.communityContentList
                items(filteredContentList) { communityContent ->
                    MessageItem(
                        isDay = isDay,
                        communityContent = communityContent
                    )
                    val space = if (communityContent.like != null) 10.dp else 20.dp
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
                onEvent(HomeViewEvent.OnClickEditFloatingBtn)
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
        state = HomeState(),
        onEvent = {}
    )
}