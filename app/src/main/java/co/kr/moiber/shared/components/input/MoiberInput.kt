package co.kr.moiber.shared.components.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.yellow01


@Composable
fun MoiberInput(
    modifier: Modifier = Modifier,
    value: String?,
    placeholder: String,
    focusRequester: FocusRequester? = null,
    onFocusChanged: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    maxLines: Int = Int.MAX_VALUE,
    maxLength: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    isCursorAlwaysToLastIndex: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isFocused) {
        onFocusChanged(isFocused)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value.orEmpty())) }
        val textFieldValue = when (isCursorAlwaysToLastIndex) {
            true -> textFieldValueState.copy(
                text = value.orEmpty(),
                selection = TextRange(index = value.orEmpty().length)
            )

            else -> textFieldValueState.copy(
                text = value.orEmpty()
            )
        }
        Box {
            val inputModifier =
                if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier
            BasicTextField(
                modifier = inputModifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                    }
                    .padding(top = 10.dp, bottom = 7.dp),
                value = textFieldValue,
                singleLine = singleLine,
                maxLines = maxLines,
                onValueChange = {
                    if (it.text.length <= maxLength) {
                        textFieldValueState = it
                        onValueChange(it.text)
                    }
                },
                textStyle = Body03.copy(textAlign = TextAlign.Center),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                cursorBrush = SolidColor(black01),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (value.isNullOrBlank()) {
                            Text(
                                style = Body03.copy(textAlign = TextAlign.Center),
                                text = placeholder,
                                color = gray01,
                            )
                        }
                    }
                    innerTextField()
                },
                enabled = enabled
            )
        }
        Column(modifier = Modifier.height(2.dp)) {
            if (enabled) {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = when {
                        isFocused -> yellow01
                        else -> gray01
                    },
                    thickness = 2.dp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoiberInputPreview1() {
    MoiberInput(
        value = null,
        placeholder = "입력해주세요",
    )
}

@Preview(showBackground = true)
@Composable
fun MoiberInputPreview2() {
    MoiberInput(
        value = "안녕",
        placeholder = "입력해주세요",
    )
}