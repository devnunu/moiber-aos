package co.kr.moiber.shared.components.modal

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