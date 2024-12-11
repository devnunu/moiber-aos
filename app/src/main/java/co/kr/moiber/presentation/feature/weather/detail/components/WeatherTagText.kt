package co.kr.moiber.presentation.feature.weather.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.Body11
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01

@Composable
fun WeatherTagText(
    text: String
) {
    Text(
        modifier = Modifier
            .background(gray01, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        style = Body11,
        text = text,
        color = white01
    )
}