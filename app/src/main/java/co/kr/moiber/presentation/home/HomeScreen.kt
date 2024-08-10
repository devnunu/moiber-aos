package co.kr.moiber.presentation.home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.kr.moiber.model.FakeHomeWeatherSummary
import co.kr.moiber.model.WeatherMessage
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
    val weatherSummary = state.weatherSummary
    val bgColor = if (weatherSummary?.isDay == true) yellow02 else black02
    weatherSummary?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = weatherSummary.weatherBgResId),
                    contentDescription = null
                )
            }
            Column {
                TopHeaderView(
                    isDay = weatherSummary.isDay
                )
                WeatherCard(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    weatherSummary = state.weatherSummary
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    DayNightText(
                        style = Body04,
                        text = Date().toFormatString("M월 d일(E)") ?: "-",
                        isDay = weatherSummary.isDay
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    weatherSummary.weatherMessageList.forEach { weatherMessage ->
                        WeatherMessageView(
                            weatherMessage = weatherMessage
                        )
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