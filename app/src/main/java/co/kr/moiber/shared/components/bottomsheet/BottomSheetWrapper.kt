package co.kr.moiber.shared.components.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import co.kr.moiber.shared.components.model.ModalState
import co.kr.moiber.shared.ui.white01
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheetWrapper(
    viewModelSheetState: ModalState<T>,
    skipPartiallyExpanded: Boolean = true,
    onCloseBottomSheet: () -> Unit,
    content: @Composable ColumnScope.(tag: T) -> Unit
) {
    val bottomSheetState: SheetState = rememberBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        onDismissBottomSheet = onCloseBottomSheet
    )

    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(viewModelSheetState) {
        if (keyboardController != null) {
            // 키보드가 켜져있는 경우 타이밍 이슈로 바텀시트가 노출되지 않으므로 딜레이를 줌
            keyboardController.hide()
            delay(200)
        }
        when (viewModelSheetState) {
            is ModalState.Closed -> bottomSheetState.hide()
            is ModalState.Opened -> bottomSheetState.show()
        }
    }
    if (viewModelSheetState is ModalState.Opened || bottomSheetState.isVisible) {
        ModalBottomSheet(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            containerColor = white01,
            sheetState = bottomSheetState,
            onDismissRequest = onCloseBottomSheet,
            content = { content(viewModelSheetState.tag) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberBottomSheetState(
    skipPartiallyExpanded: Boolean,
    onDismissBottomSheet: () -> Unit
): SheetState = rememberModalBottomSheetState(
    skipPartiallyExpanded = skipPartiallyExpanded,
    confirmValueChange = {
        if (it == SheetValue.Hidden) {
            onDismissBottomSheet()
            return@rememberModalBottomSheetState true
        }
        if (it == SheetValue.PartiallyExpanded) {
            return@rememberModalBottomSheetState false
        }
        true
    },
)
