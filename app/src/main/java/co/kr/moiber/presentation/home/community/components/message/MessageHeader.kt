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
import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.shared.components.DayNightText
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.Body10
import co.kr.moiber.shared.ui.Body11

@Composable
fun ContentMessageHeader(
    modifier: Modifier = Modifier,
    isDay: Boolean,
    communityContent: CommunityContent
) {
    val isMyContent = communityContent.isMyContent(userId = 0)
    if (isMyContent) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            DayNightText(
                style = Body11,
                isDay = isDay,
                text = "${communityContent.location} | " +
                        "${communityContent.insertTime.toFormatString("a hh:mm")}"
            )
            Spacer(modifier = Modifier.size(8.dp))
            DayNightText(
                style = Body10,
                isDay = isDay,
                text = "나"
            )
            Spacer(modifier = Modifier.size(4.dp))
            MessageFeelIcon(communityContent.feelGood)
        }
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MessageFeelIcon(communityContent.feelGood)
            Spacer(modifier = Modifier.size(4.dp))
            DayNightText(
                style = Body10,
                isDay = isDay,
                text = communityContent.userName.orEmpty()
            )
            Spacer(modifier = Modifier.size(8.dp))
            DayNightText(
                style = Body11,
                isDay = isDay,
                text = "${communityContent.location} | " +
                        "${communityContent.insertTime.toFormatString("a hh:mm")}"
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