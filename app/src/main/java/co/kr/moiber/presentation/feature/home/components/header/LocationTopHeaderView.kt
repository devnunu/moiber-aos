package co.kr.moiber.presentation.feature.home.components.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.shared.components.DayNightIcon
import co.kr.moiber.shared.components.DayNightText
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.Title03

@Composable
fun TopHeaderView(
    isDay: Boolean,
    rightIconResId: Int = R.drawable.icn_menu1,
    onClickLocation: () -> Unit,
    onClickRightIcon: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickableNonIndication {
                onClickLocation()
            }
        ) {
            DayNightIcon(
                iconResId = R.drawable.icn_location1,
                isDay = isDay
            )
            Spacer(modifier = Modifier.size(3.dp))
            DayNightText(
                style = Title03,
                isDay = isDay,
                text = "서울 성북구"
            )
            Spacer(modifier = Modifier.size(3.dp))
            DayNightIcon(
                iconResId = R.drawable.icn_drop_down1,
                isDay = isDay
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        DayNightIcon(
            modifier = Modifier.clickableNonIndication {
                onClickRightIcon()
            },
            iconResId = rightIconResId,
            isDay = isDay
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopHeaderViewPreview() {
    TopHeaderView(
        isDay = true,
        onClickLocation = {},
        onClickRightIcon = {}
    )
}