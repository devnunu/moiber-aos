package co.kr.moiber.presentation.home.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.HomeWeatherSummary
import co.kr.moiber.presentation.home.summary.components.card.WeatherIndexDetailItem
import co.kr.moiber.shared.components.VerticalDivider
import co.kr.moiber.shared.ui.Body01
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.Title01
import co.kr.moiber.shared.ui.blue01
import co.kr.moiber.shared.ui.red01
import co.kr.moiber.shared.ui.white01
import kotlin.math.abs

@Composable
fun WeatherContent(
    modifier: Modifier = Modifier,
    weatherSummary: HomeWeatherSummary,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                weatherSummary.weatherIconResId?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    style = Title01,
                    text = "${weatherSummary.currentTemp ?: "-"}°"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        style = Body09,
                        text = "↓ ${weatherSummary.minTemp ?: "-"}°",
                        color = blue01
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        style = Body09,
                        text = "↓ ${weatherSummary.maxTemp ?: "-"}°",
                        color = red01
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        style = Body09,
                        text = "체감 ${weatherSummary.apparentTemp ?: "-"}°",
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                Row {
                    val diffText = if ((weatherSummary.diffTemp ?: 0) >= 0) "높아요" else "낮아요"
                    Text(
                        style = Body01,
                        text = "어제보다 ${abs(weatherSummary.diffTemp ?: 0)}° $diffText"
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
            VerticalDivider(width = 1.dp, height = 22.dp)
            Spacer(modifier = Modifier.weight(1f))
            WeatherIndexDetailItem(
                iconResId = R.drawable.icn_wind,
                text = weatherSummary.windSpeedTxt
            )
            Spacer(modifier = Modifier.weight(1f))
            VerticalDivider(width = 1.dp, height = 22.dp)
            Spacer(modifier = Modifier.weight(1f))
            WeatherIndexDetailItem(
                iconResId = R.drawable.icn_humidity,
                text = weatherSummary.humidityTxt
            )
            Spacer(modifier = Modifier.weight(1f))
            VerticalDivider(width = 1.dp, height = 22.dp)
            Spacer(modifier = Modifier.weight(1f))
            WeatherIndexDetailItem(
                iconResId = R.drawable.icn_rain,
                text = "${weatherSummary.rainIndex}%"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherCardPreview() {
    WeatherContent(
        weatherSummary = FakeHomeWeatherSummary.getFakeModel()
    )
}