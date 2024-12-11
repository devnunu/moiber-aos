package co.kr.moiber.presentation.feature.weather.detail.components.hourview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.weather.WeatherByHour
import co.kr.moiber.shared.components.VerticalDivider
import co.kr.moiber.shared.ext.getCurrentHour
import co.kr.moiber.shared.ext.getHourDiff
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body10
import co.kr.moiber.shared.ui.Body11
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.yellow01
import java.util.Date

@Composable
fun WeatherHourViewItem(
    weatherByHour: WeatherByHour
) {
    val isNow = Date().getHourDiff(weatherByHour.date ?: Date()) == 0L
    Column(
        modifier = Modifier
            .size(width = 32.dp, height = 154.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(weatherByHour.weatherIconResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(10.dp))
        VerticalDivider(
            width = 4.dp,
            height = 34.dp,
            backgroundColor = if (isNow) yellow01 else gray01
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            style = Body07,
            text = "${weatherByHour.temperature}°",
            color = black01
        )
        Text(
            style = Body11,
            text = "${weatherByHour.humidityIndex}%",
            color = gray01
        )
        Spacer(modifier = Modifier.size(10.dp))
        val text = if (isNow) "지금" else "${weatherByHour.date?.getCurrentHour()}시"
        val textColor = if (isNow) black01 else gray01
        Text(
            style = Body10,
            text = text,
            color = textColor
        )
    }
}