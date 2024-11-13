package co.kr.moiber.presentation.feature.intro.terms.components

import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import co.kr.moiber.presentation.feature.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.check.MoiberCheckBox
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02

@Composable
fun TermsAllSelectBox(
    isAllSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableNonIndication { onClick() }
            .border(1.dp, gray02, RoundedCornerShape(8.dp))
            .padding(horizontal = 11.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MoiberCheckBox(
            isChecked = isAllSelected,
            onCheckedChange = { onClick() }
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            style = Body03,
            text = "약관 전체동의",
            color = black01
        )
    }
}