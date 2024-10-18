package co.kr.moiber.presentation.home.community.components.message

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent
import co.kr.moiber.shared.ext.combinedClickableRipple
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.Body11
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow02

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageContentView(
    communityContent: CommunityContent,
    onClickMessage: (CommunityContent) -> Unit,
    onLongClickMessage: (CommunityContent) -> Unit
) {
    val isMyContent = communityContent.isMyContent(userId = 0)
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
        modifier = Modifier
            .composed {
                if (isMyContent) {
                    Modifier.padding(end = 10.dp)
                } else {
                    Modifier.padding(start = 10.dp)
                }
            }
            .widthIn(max = 308.dp)
            .background(
                color = if (isMyContent) yellow02 else white01,
                shape = radius
            )
            .clip(radius)
            .combinedClickableRipple(
                onClick = {
                    if (!communityContent.isVan) {
                        onClickMessage(communityContent)
                    }
                },
                onLongClick = {
                    if (!communityContent.isVan) {
                        onLongClickMessage(communityContent)
                    }
                },
            )
            .padding(vertical = 10.dp, horizontal = 14.dp),
    ) {
        if (communityContent.isVan) {
            val message = if (communityContent.van?.isMyVan == true) {
                "※ 회원님이 신고한 게시글이에요."
            } else {
                "※ 다수에게 신고된 게시글이에요."
            }
            Text(
                style = Body06,
                text = message,
                color = gray01
            )
        } else {
            val hasMessage = communityContent.message?.isNotBlank() ?: false
            if (hasMessage) {
                Text(
                    style = Body08,
                    text = "${communityContent.message}"
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
            Row {
                TextWithClothIcon(
                    resId = R.drawable.icn_outerwear,
                    text = "${communityContent.outerwear}"
                )
                Spacer(modifier = Modifier.size(4.dp))
                TextWithClothIcon(
                    resId = R.drawable.icn_top,
                    text = "${communityContent.upperWear}"
                )
                Spacer(modifier = Modifier.size(4.dp))
                TextWithClothIcon(
                    resId = R.drawable.icn_pants,
                    text = "${communityContent.bottomWear}"
                )
            }
        }
    }
}

@Composable
fun TextWithClothIcon(
    resId: Int,
    text: String,
) {
    Row(
        modifier = Modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = resId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            style = Body11,
            text = text
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageContentViewPreview() {
    MessageContentView(
        communityContent = FakeCommunityContent.getFakeModel(),
        onClickMessage = {},
        onLongClickMessage = {}
    )
}