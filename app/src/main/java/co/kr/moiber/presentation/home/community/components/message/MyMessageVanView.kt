package co.kr.moiber.presentation.home.community.components.message

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.ext.clickableRipple
import co.kr.moiber.shared.ui.Body05
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01

@Composable
fun MyMessageVanView(
    onClickMyVanMessage: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(224.dp)
            .background(
                color = gray02,
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 4.dp,
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )
            )
            .padding(vertical = 12.dp, horizontal = 14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icn_caution),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                style = Body05,
                text = "신고된 게시글",
                color = black02
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            style = Body06,
            text = "내 게시글이 다수에게 신고되었어요.\n서비스 이용에 제한이 있을 수 있어요.",
            color = black02
        )
        Spacer(modifier = Modifier.size(12.dp))
        MoiberButton(
            modifier = Modifier
                .fillMaxWidth()
                .clickableRipple(onClick = { onClickMyVanMessage() }, bounded = true),
            backgroundColor = black02,
            fontColor = white01,
            fontStyle = Body05,
            buttonSize = ButtonSize.SMALL,
            text = "신고 철회 요청하기",
        )
    }
}
