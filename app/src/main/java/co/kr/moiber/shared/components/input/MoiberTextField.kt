package co.kr.moiber.shared.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.report.ReportViewEvent
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01

@Composable
fun MoiberTextField(
    modifier: Modifier = Modifier,
    height: Dp,
    value: String,
    placeHolder: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(white01, RoundedCornerShape(4.dp))
            .border(1.dp, gray02, RoundedCornerShape(4.dp))
            .padding(12.dp),
        value = value,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (value.isBlank()) {
                    Text(
                        style = Body07,
                        text = placeHolder,
                        color = gray01
                    )
                }
            }
            innerTextField()
        }
    )
}