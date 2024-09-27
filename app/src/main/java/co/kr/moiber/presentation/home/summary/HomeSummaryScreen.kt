package co.kr.moiber.presentation.home.summary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.Weather
import co.kr.moiber.presentation.home.HomeState
import co.kr.moiber.presentation.home.HomeViewEvent
import co.kr.moiber.presentation.home.summary.components.animation.HomeAnimationVisibility
import co.kr.moiber.presentation.home.summary.components.card.WeatherCard
import co.kr.moiber.presentation.home.summary.components.message.WeatherMessageView
import co.kr.moiber.shared.components.DayNightText
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ui.Body04
import java.util.Date

@Composable
fun HomeSummaryScreen(
    isVisible: Boolean,
    state: HomeState,
    onEvent: (HomeViewEvent) -> Unit
) {
    val weatherSummary = state.weatherSummary

    weatherSummary?.let {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                HomeAnimationVisibility(
                    isSlide = true,
                    visible = isVisible,
                    duration = 300,
                    delay = 350 + (200 * weatherSummary.weatherMessageList.size) + 500
                ) {
                    val bottomPadding = when(weatherSummary.currentWeather) {
                        Weather.SNOWY,
                        Weather.RAINY-> 8.dp
                        else -> 20.dp
                    }
                    Image(
                        modifier = Modifier.padding(bottom = bottomPadding),
                        painter = painterResource(id = weatherSummary.characterResId),
                        contentDescription = null
                    )
                }
            }
            Column {
                HomeAnimationVisibility(
                    visible = isVisible,
                    duration = 1200,
                    delay = 250
                ) {
                    WeatherCard(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        weatherSummary = state.weatherSummary
                    )
                }
                Box {
                    HomeAnimationVisibility(
                        visible = isVisible,
                        duration = 400,
                        delay = 150
                    ) {
                        Image(
                            modifier = Modifier.padding(top = 8.dp),
                            painter = painterResource(id = weatherSummary.weatherBgResId),
                            contentDescription = null
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 22.dp)
                    ) {
                        Spacer(modifier = Modifier.size(20.dp))
                        HomeAnimationVisibility(
                            visible = isVisible,
                            duration = 250,
                            delay = 350
                        ) {
                            DayNightText(
                                style = Body04,
                                text = Date().toFormatString("M월 d일(E)") ?: "-",
                                isDay = weatherSummary.isDay
                            )
                        }
                        Spacer(modifier = Modifier.size(6.dp))
                        weatherSummary.weatherMessageList.forEachIndexed { index, weatherMessage ->
                            HomeAnimationVisibility(
                                visible = isVisible,
                                duration = 200,
                                delay = 350 + (200 * index)
                            ) {
                                WeatherMessageView(
                                    weatherMessage = weatherMessage
                                )
                            }
                            Spacer(modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeSummaryScreen(
        isVisible = true,
        state = HomeState(
            weatherSummary = FakeHomeWeatherSummary.getFakeModel()
        ),
        onEvent = {}
    )
}