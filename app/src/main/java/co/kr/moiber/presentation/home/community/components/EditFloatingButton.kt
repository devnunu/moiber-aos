package co.kr.moiber.presentation.home.community.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01

@Composable
fun EditFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    LargeFloatingActionButton(
        modifier = modifier.size(64.dp),
        containerColor = white01,
        onClick = { onClick() },
        shape = CircleShape,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_pen_24_2),
            contentDescription = null,
            tint = black02
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditFloatingButtonPreview() {
    EditFloatingButton(
        onClick = {}
    )
}