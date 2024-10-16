package co.kr.moiber.shared.components.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.components.model.ModalState
import co.kr.moiber.shared.components.scaffold.ScaffoldBottomSheetView
import co.kr.moiber.shared.ui.white01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberScaffoldBottomSheetView(
    viewModelSheetState: ModalState<*>,
    onCloseBottomSheet: () -> Unit,
    skipPartiallyExpanded: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
): ScaffoldBottomSheetView {
    val modalBottomSheetState: SheetState = rememberBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        onDismissBottomSheet = onCloseBottomSheet
    )
    return ScaffoldBottomSheetView(
        viewModelSheetState = viewModelSheetState,
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        if (viewModelSheetState is ModalState.Opened || modalBottomSheetState.isVisible) {
            ModalBottomSheet(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                containerColor = white01,
                sheetState = modalBottomSheetState,
                onDismissRequest = {},
                content = content
            )
        }
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
