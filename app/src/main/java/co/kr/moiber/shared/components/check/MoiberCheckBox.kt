package co.kr.moiber.shared.components.check

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.presentation.feature.home.community.HomeCommunityViewEvent

@Composable
fun MoiberCheckBox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    IconToggleButton(
        modifier = Modifier.size(18.dp),
        checked = isChecked,
        onCheckedChange = { onCheckedChange(it) }
    ) {
        Image(
            painter = painterResource(
                if (isChecked) R.drawable.icn_check_on
                else R.drawable.icn_check_off
            ),
            contentDescription = null,
        )
    }
}