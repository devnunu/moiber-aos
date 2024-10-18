package co.kr.moiber.presentation.home.community.components.message

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent

@Composable
fun MessageItem(
    isDay: Boolean,
    communityContent: CommunityContent,
    onClickMyVanMessage: () -> Unit,
    onClickMessage: (CommunityContent) -> Unit,
    onLongClickMessage: (CommunityContent) -> Unit
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
                    communityContent = communityContent,
                    onClickMessage = onClickMessage,
                    onLongClickMessage = onLongClickMessage,
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
        onClickMyVanMessage = {},
        onClickMessage = {},
        onLongClickMessage = {}
    )
}