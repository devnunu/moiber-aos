package co.kr.moiber.presentation.home.components.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.WeatherMessage
import co.kr.moiber.model.WeatherMessageType
import co.kr.moiber.shared.ui.Body02
import co.kr.moiber.shared.ui.white02

@Composable
fun WeatherMessageView(
    weatherMessage: WeatherMessage
) {
    val bgColor = when (weatherMessage.type) {
        WeatherMessageType.CAUTION -> Color(0xFFFFCACA)
        WeatherMessageType.WARNING -> Color(0xFFFFEECA)
        else -> white02
    }
    Row(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
    ) {
        if (weatherMessage.type?.iconResId != null) {
            Image(
                painter = painterResource(id = weatherMessage.type.iconResId),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            style = Body02,
            text = weatherMessage.message ?: "-"
        )
    }
}