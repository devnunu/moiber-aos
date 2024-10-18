package co.kr.moiber.presentation.home.components.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.home.HomeViewEvent
import co.kr.moiber.shared.components.popup.MoiberPopUp
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.gray01

@Composable
fun HomeLongPressPopUp(
    onEvent: (HomeViewEvent) -> Unit
) {
    MoiberPopUp(
        onDismissRequest = { onEvent(HomeViewEvent.OnCloseDialog) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 15.dp),
                text = "공감하기"
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = gray01
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .clickableNonIndication {
                        onEvent(HomeViewEvent.OnClickDialogReportBtn)
                    },
                text = "신고하기"
            )
        }
    }
}