package co.kr.moiber.shared.components.modal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MoiberPopUp(
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    onDismissRequest: (DialogBtn) -> Unit = {},
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest(DialogBtn.DISMISS) },
        properties = properties
    ) {
        Surface(
            modifier = Modifier.padding(horizontal = 20.dp),
            color = Color.White,
            shape = RoundedCornerShape(12.dp)
        ) {
            Box {
                content()
            }
        }
    }
}