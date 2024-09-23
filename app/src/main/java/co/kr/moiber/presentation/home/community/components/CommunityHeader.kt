package co.kr.moiber.presentation.home.community.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.gray01
import java.util.Date

@Composable
fun CommunityHeader(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val todayDateTxt = Date().toFormatString("yyyy.MM.dd").orEmpty()
        Text(
            style = Body09,
            text = todayDateTxt,
            color = gray01
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = Body06,
            text = "내 기록",
            color = black02
        )
        Spacer(modifier = Modifier.padding(end = 10.dp))
        MoiberSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommunityHeaderPreview() {
    CommunityHeader(
        checked = false,
        onCheckedChange = {}
    )
}