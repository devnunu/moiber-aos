package co.kr.moiber.presentation.home.summary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.Weather
import co.kr.moiber.presentation.home.summary.components.animation.HomeAnimationVisibility
import co.kr.moiber.presentation.home.components.weather.WeatherContent
import co.kr.moiber.presentation.home.summary.components.message.WeatherMessageView
import co.kr.moiber.shared.components.DayNightText
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.white01
import java.util.Date

@Composable
fun HomeSummaryScreen(
    viewModel: HomeSummaryViewModel = hiltViewModel(),
    isVisible: Boolean,
) {
    HomeSummaryScreen(
        isVisible = isVisible,
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HomeSummaryScreen(
    isVisible: Boolean,
    state: HomeSummaryState,
    onEvent: (HomeSummaryViewEvent) -> Unit
) {
    val weatherSummary = state.weatherSummary

    weatherSummary?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 41.dp)
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
                    val bottomPadding = when (weatherSummary.currentWeather) {
                        Weather.SNOWY,
                        Weather.RAINY -> 8.dp

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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(white01, RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp, vertical = 20.dp)
                    ) {
                        WeatherContent(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            weatherSummary = state.weatherSummary
                        )
                    }
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
                                style = Body07,
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
        state = HomeSummaryState(
            weatherSummary = FakeHomeWeatherSummary.getFakeModel()
        ),
        onEvent = {}
    )
}