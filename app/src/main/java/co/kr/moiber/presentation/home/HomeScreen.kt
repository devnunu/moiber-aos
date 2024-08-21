package co.kr.moiber.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.kr.moiber.model.FakeHomeWeatherSummary
import co.kr.moiber.model.WeatherMessage
import co.kr.moiber.presentation.home.components.animation.HomeAnimationVisibility
import co.kr.moiber.presentation.home.components.card.WeatherCard
import co.kr.moiber.presentation.home.components.header.TopHeaderView
import co.kr.moiber.presentation.home.components.message.WeatherMessageView
import co.kr.moiber.shared.components.DayNightText
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.black
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow02
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
    onEvent: (HomeViewEvent) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    val weatherSummary = state.weatherSummary
    val bgColor = if (weatherSummary?.isDay == true) yellow02 else black02

    LaunchedEffect(Unit) {
        delay(150)
        isVisible = true
    }
    weatherSummary?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterStart),
            ) {
                HomeAnimationVisibility(
                    visible = isVisible,
                    duration = 400,
                    delay = 150
                ) {
                    Image(
                        painter = painterResource(id = weatherSummary.weatherBgResId),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                HomeAnimationVisibility(
                    isSlide = true,
                    visible = isVisible,
                    duration = 300,
                    delay = 350 + (200 * weatherSummary.weatherMessageList.size) + 500
                ) {
                    Image(
                        painter = painterResource(id = weatherSummary.characterResId),
                        contentDescription = null
                    )
                }
            }
            Column {
                HomeAnimationVisibility(
                    visible = isVisible,
                    duration = 400,
                    delay = 150
                ) {
                    TopHeaderView(
                        isDay = weatherSummary.isDay
                    )
                }
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
                Spacer(modifier = Modifier.size(16.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(
            weatherSummary = FakeHomeWeatherSummary.getFakeModel()
        ),
        onEvent = {}
    )
}