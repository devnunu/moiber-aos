package co.kr.moiber.presentation.home.community.components.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.red02
import co.kr.moiber.shared.ui.white01

@Composable
fun LikeTag(
    like: Int?,
    isMyLike: Boolean
) {
    Row(
        modifier = Modifier
            .background(if (isMyLike) red02 else white01, RoundedCornerShape(100.dp))
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = R.drawable.icn_heart),
            contentDescription = null
        )
        Text(
            style = Body09,
            text = like?.toString().orEmpty(),
            color = black02
        )
    }
}