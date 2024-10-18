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
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.FakeCommunityMessage

@Composable
fun MessageItem(
    isDay: Boolean,
    communityMessage: CommunityMessage,
    onClickMyVanMessage: () -> Unit,
    onClickMessage: (CommunityMessage) -> Unit,
    onLongClickMessage: (CommunityMessage) -> Unit
) {
    val isMyContent = communityMessage.isMyContent(userId = 0)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isMyContent) Alignment.End else Alignment.Start
    ) {
        ContentMessageHeader(
            isDay = isDay,
            communityMessage = communityMessage
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column(
            horizontalAlignment = if (isMyContent) Alignment.Start else Alignment.End
        ) {
            if (communityMessage.isVan && isMyContent) {
                MyMessageVanView(
                    onClickMyVanMessage = onClickMyVanMessage
                )
            } else {
                MessageContentView(
                    communityMessage = communityMessage,
                    onClickMessage = onClickMessage,
                    onLongClickMessage = onLongClickMessage,
                )

                if (communityMessage.like != null) {
                    Spacer(modifier = Modifier.size(5.dp))
                    MessageLikeTag(
                        like = communityMessage.like
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
        communityMessage = FakeCommunityMessage.getFakeModel(),
        onClickMyVanMessage = {},
        onClickMessage = {},
        onLongClickMessage = {}
    )
}