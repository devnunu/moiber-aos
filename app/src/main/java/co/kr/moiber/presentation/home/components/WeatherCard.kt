package co.kr.moiber.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.model.HomeWeatherSummary
import co.kr.moiber.model.Weather
import co.kr.moiber.shared.ui.Body01
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Title01
import co.kr.moiber.shared.ui.black
import co.kr.moiber.shared.ui.blue
import co.kr.moiber.shared.ui.gray
import co.kr.moiber.shared.ui.red
import co.kr.moiber.shared.ui.white01
import kotlin.math.abs

@Composable
fun WeatherCard(
    weatherSummary: HomeWeatherSummary,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(white01, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                weatherSummary.currentWeather?.iconResId?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    style = Title01,
                    text = "${weatherSummary.currentTemp ?: "-"}°"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        style = Body06,
                        text = "↓ ${weatherSummary.minTemp ?: "-"}°",
                        color = blue
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        style = Body06,
                        text = "↓ ${weatherSummary.maxTemp ?: "-"}°",
                        color = red
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        style = Body06,
                        text = "체감 ${weatherSummary.apparentTemp ?: "-"}°C",
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                Row {
                    val diffText = if ((weatherSummary.diffTemp ?: 0) >= 0) "높아요" else "낮아요"
                    Text(
                        style = Body01,
                        text = "어제보다 ${abs(weatherSummary.diffTemp ?: 0)}° ${diffText}"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(19.dp))
        Row(
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            WeatherIndexDetailItem(
                iconResId = R.drawable.icn_dust,
                text = weatherSummary.dustTxt
            )
            Spacer(modifier = Modifier.weight(1f))
            VerticalDivider()
            Spacer(modifier = Modifier.weight(1f))
            WeatherIndexDetailItem(
                iconResId = R.drawable.icn_wind,
                text = weatherSummary.windSpeedTxt
            )
            Spacer(modifier = Modifier.weight(1f))
            VerticalDivider()
            Spacer(modifier = Modifier.weight(1f))
            WeatherIndexDetailItem(
                iconResId = R.drawable.icn_humidity,
                text = weatherSummary.humidityTxt
            )
            Spacer(modifier = Modifier.weight(1f))
            VerticalDivider()
            Spacer(modifier = Modifier.weight(1f))
            WeatherIndexDetailItem(
                iconResId = R.drawable.icn_rain,
                text = "${weatherSummary.rainIndex}%"
            )
        }
    }
}

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
            tint = black
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            style = Body03,
            text = text
        )
    }
}

@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(22.dp)
            .background(gray)
    )
}

@Preview
@Composable
fun WeatherCardPreview() {
    WeatherCard(
        weatherSummary = HomeWeatherSummary(
            currentWeather = Weather.DAY_SUNNY,
            currentTemp = 32,
            maxTemp = 32,
            minTemp = 23,
            apparentTemp = 28,
            diffTemp = 3,
            dustIndex = 81,
            windSpeedIndex = 2,
            humidityIndex = 81,
            rainIndex = 60
        )
    )
}