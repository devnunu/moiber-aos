package co.kr.moiber.presentation.home.summary.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.black01

@Composable
fun WeatherIndexDetailItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    text: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = black01
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            style = Body06,
            text = text
        )
    }
}

@Preview
@Composable
fun WeatherIndexDetailItemPreview() {
    WeatherIndexDetailItem(
        iconResId = R.drawable.icn_dust,
        text = "나쁨"
    )
}