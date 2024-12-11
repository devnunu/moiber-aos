package co.kr.moiber.presentation.feature.weather.detail.components.hourview

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.weather.FakeWeatherDetail
import co.kr.moiber.model.weather.WeatherByHour
import co.kr.moiber.shared.ext.getHourDiff
import co.kr.moiber.shared.ui.yellow03
import java.util.Date

@Composable
fun WeatherHourViewView(
    weatherByHourList: List<WeatherByHour>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(yellow03, RoundedCornerShape(8.dp))
            .padding(top = 11.dp, bottom = 13.dp, start = 14.dp, end = 14.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        weatherByHourList.forEachIndexed { index, weatherByHour ->
            if (index != 0) {
                Spacer(modifier = Modifier.size(14.dp))
            }
            WeatherHourViewItem(
                weatherByHour = weatherByHour
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherHourViewViewPreview() {
    WeatherHourViewView(
        weatherByHourList = FakeWeatherDetail.getFakeModel().weatherByHourList
    )
}