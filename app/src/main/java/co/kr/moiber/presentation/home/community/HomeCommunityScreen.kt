package co.kr.moiber.presentation.home.community

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.home.HomeState
import co.kr.moiber.presentation.home.HomeViewEvent
import co.kr.moiber.presentation.home.community.components.CommunityHeader
import co.kr.moiber.presentation.home.community.components.EditFloatingButton
import co.kr.moiber.shared.ui.yellow01

@Composable
fun HomeCommunityScreen(
    state: HomeState,
    onEvent: (HomeViewEvent) -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
        ) {
            CommunityHeader(
                modifier = Modifier.padding(vertical = 7.dp),
                checked = true,
                onCheckedChange = {}
            )
            Text(text = "커뮤니티")
        }
        EditFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 40.dp, end = 22.dp),
            onClick = {}
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