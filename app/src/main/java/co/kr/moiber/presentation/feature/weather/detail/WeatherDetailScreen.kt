package co.kr.moiber.presentation.feature.weather.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.shared.components.scaffold.MoiberScaffold

@Composable
fun WeatherDetailScreen(
    viewModel: WeatherDetailViewModel = hiltViewModel(),
    navController: NavController,
) {
    WeatherDetailScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun WeatherDetailScreen(
    state: WeatherDetailState,
    onEvent: (WeatherDetailViewEvent) -> Unit
) {
    MoiberScaffold {

    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDetailScreenPreview() {
    WeatherDetailScreen(
        state = WeatherDetailState(),
        onEvent = {}
    )
}

