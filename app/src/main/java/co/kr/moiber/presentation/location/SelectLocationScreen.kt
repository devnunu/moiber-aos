package co.kr.moiber.presentation.location

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.presentation.location.components.SelectLocationInput
import co.kr.moiber.shared.ui.Body02
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01

@Composable
fun SelectLocationScreen(
    navController: NavController,
    viewModel: SelectLocationViewModel = hiltViewModel()
) {
    SelectLocationScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun SelectLocationScreen(
    state: SelectLocationState,
    onEvent: (SelectLocationViewEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icn_close),
                contentDescription = null,

                )
            Text(
                modifier = Modifier.weight(1f),
                style = Body02,
                text = "지역 선택",
                color = black01,
                textAlign = TextAlign.Center
            )
            Text(
                style = Body03,
                text = "편집",
                color = gray01
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.size(11.dp))
            SelectLocationInput(
                value = state.locationText.orEmpty(),
                onValueChange = { text ->
                    onEvent(SelectLocationViewEvent.OnChangedLocationText(text))
                }
            )
            Spacer(modifier = Modifier.size(18.dp))
            Row {
                Icon(
                    painter = painterResource(R.drawable.icn_location1),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    style = Body07,
                    text = "현재 위치",
                    color = gray01
                )
            }
            Spacer(modifier = Modifier.size(16.dp))

            Row {
                Icon(
                    painter = painterResource(R.drawable.icn_location1),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    style = Body07,
                    text = "최근 검색",
                    color = gray01
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectLocationScreenPreview() {
    SelectLocationScreen(
        state = SelectLocationState(),
        onEvent = {}
    )
}