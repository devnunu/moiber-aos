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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01

@Composable
fun MoiberTextField(
    modifier: Modifier = Modifier,
    height: Dp? = null,
    value: String,
    placeHolder: String,
    padding: Dp = 12.dp,
    radius: Dp = 4.dp,
    backgroundColor: Color = white01,
    borderColor: Color = gray02,
    onValueChange: (String) -> Unit
) {
    val childModifier = if (height == null) modifier else modifier.height(height)

    BasicTextField(
        modifier = childModifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(radius))
            .border(1.dp, borderColor, RoundedCornerShape(radius))
            .padding(padding),
        value = value,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (value.isBlank()) {
                    Text(
                        style = Body09,
                        text = placeHolder,
                        color = gray01
                    )
                }
            }
            innerTextField()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MoiberTextFieldPreview() {
    MoiberTextField(
        height = 124.dp,
        value = "",
        placeHolder = "직접 입력하기(공백 포함 최대 150자)",
        onValueChange = { text ->

        }
    )
}