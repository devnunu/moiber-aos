package co.kr.moiber.presentation.home.community.components.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.shared.components.DayNightText
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ui.Body10
import co.kr.moiber.shared.ui.Body11

@Composable
fun ContentMessageHeader(
    modifier: Modifier = Modifier,
    isDay: Boolean,
    communityMessage: CommunityMessage
) {
    val isMyContent = communityMessage.isMyContent(userId = 0)
    if (isMyContent) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            DayNightText(
                style = Body11,
                isDay = isDay,
                text = "${communityMessage.location} | " +
                        "${communityMessage.insertTime.toFormatString("a hh:mm")}"
            )
            Spacer(modifier = Modifier.size(8.dp))
            DayNightText(
                style = Body10,
                isDay = isDay,
                text = "나"
            )
            Spacer(modifier = Modifier.size(4.dp))
            MessageFeelIcon(communityMessage.feelGood)
        }
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MessageFeelIcon(communityMessage.feelGood)
            Spacer(modifier = Modifier.size(4.dp))
            DayNightText(
                style = Body10,
                isDay = isDay,
                text = communityMessage.userName.orEmpty()
            )
            Spacer(modifier = Modifier.size(8.dp))
            DayNightText(
                style = Body11,
                isDay = isDay,
                text = "${communityMessage.location} | " +
                        "${communityMessage.insertTime.toFormatString("a hh:mm")}"
            )
        }
    }

}

@Composable
fun MessageFeelIcon(feelGood: Boolean) {
    Image(
        modifier = Modifier.size(28.dp),
        painter = painterResource(
            id = if (feelGood) R.drawable.img_good else R.drawable.img_cool
        ),
        contentDescription = null
    )
}