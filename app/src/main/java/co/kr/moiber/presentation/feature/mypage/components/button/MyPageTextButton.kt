package co.kr.moiber.presentation.feature.mypage.components.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ext.clickableRipple
import co.kr.moiber.shared.ui.Body05

@Composable
fun MyPageTextButton(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableRipple(bounded = true) {
                onClick()
            }
            .padding(horizontal = 25.dp, vertical = 30.5.dp)
    ) {
        Text(
            style = Body05,
            text = text
        )
    }
}