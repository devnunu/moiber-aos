package co.kr.moiber.presentation.feature.weather.detail.components.dayview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.weather.FakeWeatherDetail
import co.kr.moiber.model.weather.WeatherByDate

@Composable
fun WeatherDateView(
    weatherByDateList: List<WeatherByDate>,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        weatherByDateList.forEachIndexed { index, weatherByDate ->
            if (index != 0) {
                Spacer(modifier = Modifier.size(14.dp))
            }
            WeatherDateViewItem(
                weatherByDate = weatherByDate
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDayViewPreview() {
    WeatherDateView(
        weatherByDateList = FakeWeatherDetail.getFakeModel().weatherByDateList
    )
}