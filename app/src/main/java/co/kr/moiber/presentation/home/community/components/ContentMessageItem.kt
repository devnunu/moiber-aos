package co.kr.moiber.presentation.home.community.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.white01
import java.util.Date

@Composable
fun ContentMessageItem(
    communityContent: CommunityContent
) {
    Column {
        Row {
            Text(
                style = Body08,
                text = communityContent.userName.orEmpty()
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                style = Body09,
                text = "${communityContent.location} | ${
                    communityContent.insertTime.toFormatString(
                        "a hh:mm"
                    )
                }"
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .background(
                    color = white01,
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )
                .padding(vertical = 10.dp, horizontal = 14.dp),
        ) {
            Text(
                style = Body06,
                text = "${communityContent.message}"
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
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
                Spacer(modifier = Modifier.weight(1f))
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
                Spacer(modifier = Modifier.weight(1f))
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
    }
}

@Preview(showBackground = true)
@Composable
fun ContentMessageItemPreview() {
    ContentMessageItem(
        communityContent = FakeCommunityContent.getFakeModel()
    )
}