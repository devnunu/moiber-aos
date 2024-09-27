package co.kr.moiber.presentation.home.community.components.message

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow02

@Composable
fun ContentMessageItem(
    isDay: Boolean,
    communityContent: CommunityContent
) {
    val isMyContent = communityContent.isMyContent(userId = 0)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isMyContent) Alignment.End else Alignment.Start
    ) {
        ContentMessageHeader(
            isDay = isDay,
            communityContent = communityContent
        )
        Spacer(modifier = Modifier.size(4.dp))
        val radius = if (isMyContent) {
            RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 4.dp,
                bottomStart = 12.dp,
                bottomEnd = 12.dp
            )
        } else {
            RoundedCornerShape(
                topStart = 4.dp,
                topEnd = 12.dp,
                bottomStart = 12.dp,
                bottomEnd = 12.dp
            )
        }
        Column(
            horizontalAlignment = if (isMyContent) Alignment.Start else Alignment.End
        ) {
            Column(
                modifier = Modifier
                    .composed {
                        if (isMyContent) {
                            Modifier.padding(end = 10.dp)
                        } else {
                            Modifier.padding(start = 10.dp)
                        }
                    }
                    .background(
                        color = if (isMyContent) yellow02 else white01,
                        shape = radius
                    )
                    .padding(vertical = 10.dp, horizontal = 14.dp),
            ) {
                val hasMessage = communityContent.message?.isNotBlank() ?: false
                if (hasMessage) {
                    Text(
                        style = Body06,
                        text = "${communityContent.message}"
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Row {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "\uD83E\uDD7C"
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            style = Body09,
                            text = "${communityContent.outerwear}"
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "\uD83D\uDC55"
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            style = Body09,
                            text = "${communityContent.upperWear}"
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "\uD83D\uDC55"
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            style = Body09,
                            text = "${communityContent.bottomWear}"
                        )
                    }
                }
            }
            if (communityContent.like != null) {
                Spacer(modifier = Modifier.size(5.dp))
                LikeTag(
                    like = communityContent.like,
                    isMyLike = communityContent.isMyLike
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentMessageItemPreview() {
    ContentMessageItem(
        isDay = true,
        communityContent = FakeCommunityContent.getFakeModel()
    )
}