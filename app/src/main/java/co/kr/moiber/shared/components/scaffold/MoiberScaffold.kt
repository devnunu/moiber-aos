package co.kr.moiber.shared.components.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.kr.moiber.shared.ui.white01


@Composable
fun MoiberScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    backgroundColor: Color = white01,
    contentColor: Color = contentColorFor(backgroundColor),
    // content
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = bottomBar,
        containerColor = backgroundColor,
        contentColor = contentColor,
    ) { paddingValues ->
        topBar()
        content(paddingValues)
    }
}