package co.kr.moiber.presentation.home.community.components.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow02

@Composable
fun MessageItem(
    isDay: Boolean,
    communityContent: CommunityContent,
    onClickMyVanMessage: () -> Unit
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
        Column(
            horizontalAlignment = if (isMyContent) Alignment.Start else Alignment.End
        ) {
            if (communityContent.isVan && isMyContent) {
                MyMessageVanView(
                    onClickMyVanMessage = onClickMyVanMessage
                )
            } else {
                MessageContentView(
                    communityContent = communityContent
                )

                if (communityContent.like != null) {
                    Spacer(modifier = Modifier.size(5.dp))
                    MessageLikeTag(
                        like = communityContent.like
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageItemPreview() {
    MessageItem(
        isDay = true,
        communityContent = FakeCommunityContent.getFakeModel(),
        onClickMyVanMessage = {}
    )
}