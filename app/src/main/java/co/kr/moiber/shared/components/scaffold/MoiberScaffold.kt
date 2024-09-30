package co.kr.moiber.shared.components.scaffold

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import co.kr.moiber.shared.components.model.ModalState
import co.kr.moiber.shared.ui.white01
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
data class ScaffoldBottomSheetView(
    val viewModelSheetState: ModalState<*>,
    val sheetState: SheetState,
    val sheetShape: Shape,
    val sheetContent: @Composable ColumnScope.() -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoiberScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    backgroundColor: Color = white01,
    contentColor: Color = contentColorFor(backgroundColor),
    // bottom Sheet
    bottomSheetView: ScaffoldBottomSheetView? = null,
    // content
    content: @Composable (PaddingValues) -> Unit,
) {
    val child = @Composable { childModifier: Modifier ->
        Scaffold(
            modifier = childModifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            topBar = topBar,
            bottomBar = bottomBar,
            containerColor = backgroundColor,
            contentColor = contentColor,
        ) { paddingValues ->
                content(paddingValues)
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    if (bottomSheetView != null) {
        LaunchedEffect(bottomSheetView.viewModelSheetState) {
            if (keyboardController != null) {
                // 키보드가 켜져있는 경우 타이밍 이슈로 바텀시트가 노출되지 않으므로 딜레이를 줌
                keyboardController.hide()
                delay(200)
            }
            when (bottomSheetView.viewModelSheetState) {
                is ModalState.Closed -> bottomSheetView.sheetState.hide()
                is ModalState.Opened -> bottomSheetView.sheetState.show()
            }
        }
        ModalBottomSheet(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            sheetState = bottomSheetView.sheetState,
            onDismissRequest = {},
            content = {
                child(modifier)
            }
        )
    } else {
        child(modifier)
    }
}