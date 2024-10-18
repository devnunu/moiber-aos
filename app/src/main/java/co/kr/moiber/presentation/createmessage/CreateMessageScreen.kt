package co.kr.moiber.presentation.createmessage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CreateMessageScreen(
    navController: NavController,
    viewModel: CreateMessageViewModel = hiltViewModel()
) {
    CreateMessageScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CreateMessageScreen(
    state: CreateMessageState,
    onEvent: (CreateMessageViewEvent) -> Unit
) {

}