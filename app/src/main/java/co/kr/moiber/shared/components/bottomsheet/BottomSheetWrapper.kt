package co.kr.moiber.shared.components.bottomsheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
                sheetState = modalBottomSheetState,
                onDismissRequest = {},
                content = {
                    content()
                }
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetWrapper(
    modalBottomSheetState: SheetState,
    onClickBack: () -> Unit,
    content: @Composable () -> Unit
) {
    // bottomSheet 내부 영역이 없으면 앵커를 잡지 못해서 에러 발생하므로 padding 으로 일정 영역을 확보해줌
    // https://stackoverflow.com/questions/69346646/jetpack-compose-bottomsheet-with-empty-sheet-content-is-always-expnaded
    Box(
        modifier = Modifier.padding(bottom = 1.dp)
    ) {
        content()
        BackHandler(
            enabled = modalBottomSheetState.isVisible,
            onBack = onClickBack
        )
    }
}
