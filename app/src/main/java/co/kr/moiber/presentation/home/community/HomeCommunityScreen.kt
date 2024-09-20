package co.kr.moiber.presentation.home.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.kr.moiber.presentation.home.HomeState
import co.kr.moiber.presentation.home.HomeViewEvent

@Composable
fun HomeCommunityScreen(
    state: HomeState,
    onEvent: (HomeViewEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "커뮤니티")
    }
}