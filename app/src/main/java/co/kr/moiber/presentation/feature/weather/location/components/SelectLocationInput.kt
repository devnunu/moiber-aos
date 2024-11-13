package co.kr.moiber.presentation.feature.weather.location.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.R
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01

@Composable
fun SelectLocationInput(
    modifier: Modifier = Modifier,
    value: String,
    placeHolder: String = "지금, 이 동네 날씨는?!",
    onValueChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .border(2.dp, gray02, RoundedCornerShape(40.dp))
            .padding(vertical = 12.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.icn_search_l),
            contentDescription = null,
            tint = gray01
        )
        Spacer(modifier = Modifier.size(2.5.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(white01),
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
}

@Preview(showBackground = true)
@Composable
fun SelectLocationInputPreview() {
    SelectLocationInput(
        value = "",
        onValueChange = {}
    )
}