package co.kr.moiber.presentation.feature.weather.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.model.weather.FakeHomeWeatherSummary
import co.kr.moiber.model.weather.FakeWeatherDetail
import co.kr.moiber.presentation.feature.home.community.HomeCommunityViewEvent
import co.kr.moiber.presentation.feature.home.components.header.TopHeaderView
import co.kr.moiber.presentation.feature.home.components.weather.WeatherContent
import co.kr.moiber.presentation.feature.weather.detail.components.WeatherTagText
import co.kr.moiber.presentation.feature.weather.detail.components.hourview.WeatherHourViewView
import co.kr.moiber.presentation.navigation.NavRoute
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body11
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01

@Composable
fun WeatherDetailScreen(
    viewModel: WeatherDetailViewModel = hiltViewModel(),
    navController: NavController,
) {
    val context = LocalContext.current
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is WeatherDetailSideEffect.NavigateToWeatherSelectLocation -> {
                navController.navigate(NavRoute.WeatherSelectLocation)
            }
        }
    }

    WeatherDetailScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent,
        onClickBack = {
            navController.popBackStack()
        }
    )
}

@Composable
fun WeatherDetailScreen(
    state: WeatherDetailState,
    onEvent: (WeatherDetailViewEvent) -> Unit,
    onClickBack: () -> Unit
) {
    val weatherSummary = state.weatherSummary
    val weatherDetailSummary = state.weatherDetail

    var isDay by remember { mutableStateOf(true) }
    MoiberScaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(white01)
        ) {
            TopHeaderView(
                isDay = isDay,
                rightIconResId = R.drawable.icn_close,
                onClickLocation = {
                    onEvent(WeatherDetailViewEvent.OnClickTopHeaderLocation)
                },
                onClickRightIcon = onClickBack
            )
            Spacer(modifier = Modifier.size(8.dp))
            weatherSummary?.let {
                Column(
                    Modifier.padding(horizontal = 16.dp)
                ) {
                    WeatherContent(
                        weatherSummary = weatherSummary
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row {
                        WeatherTagText(
                            text = "미세먼지"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        WeatherTagText(
                            text = "풍속"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        WeatherTagText(
                            text = "습도"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        WeatherTagText(
                            text = "강수확률"
                        )
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    WeatherHourViewView(
                        weatherByHourList = weatherDetailSummary?.weatherByHourList ?: emptyList()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDetailScreenPreview() {
    WeatherDetailScreen(
        state = WeatherDetailState(
            weatherSummary = FakeHomeWeatherSummary.getFakeModel(),
            weatherDetail = FakeWeatherDetail.getFakeModel()
        ),
        onEvent = {},
        onClickBack = {},
    )
}

