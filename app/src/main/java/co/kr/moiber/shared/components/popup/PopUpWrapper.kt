package co.kr.moiber.shared.components.popup

import androidx.compose.runtime.Composable
import co.kr.moiber.shared.components.model.ModalState

@Composable
fun <T> PopUpWrapper(
    dialogState: ModalState<T>,
    content: @Composable (tag: T) -> Unit
) {
    if (dialogState is ModalState.Opened) {
        content(dialogState.tag)
    }
}