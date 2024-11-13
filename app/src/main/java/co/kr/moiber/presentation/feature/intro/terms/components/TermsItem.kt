package co.kr.moiber.presentation.feature.intro.terms.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.moiber.data.terms.Terms
import co.kr.moiber.shared.components.check.MoiberCheckBox
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.Body03
import co.kr.moiber.shared.ui.black01

@Composable
fun TermsItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    terms: Terms,
    onClickItem: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickableNonIndication {
                onClickItem(terms.termsId)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        MoiberCheckBox(
            isChecked = isSelected,
            onCheckedChange = { onClickItem(terms.termsId) }
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            style = Body03,
            text = if (terms.isRequired) "(필수) ${terms.title}" else terms.title,
            color = black01
        )
    }
}