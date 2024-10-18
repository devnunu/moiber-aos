package co.kr.moiber.presentation.createmessage.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ext.clickableRipple
import co.kr.moiber.shared.ui.Body05
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.yellow01

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WearListView(
    list: List<String>,
    selectedItem: String?,
    onClickItem: (String) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.size(30.dp))
        FlowRow(
            modifier = Modifier.padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
        ) {
            list.forEach { item ->
                WearTag(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .clickableNonIndication { onClickItem(item) },
                    text = item,
                    isSelected = selectedItem == item
                )
            }
        }
    }
}

@Composable
fun WearTag(
    modifier: Modifier,
    text: String,
    isSelected: Boolean
) {
    val color = if (isSelected) yellow01 else gray02
    Text(
        modifier = modifier
            .border(1.dp, color, RoundedCornerShape(24.dp))
            .padding(horizontal = 22.dp, vertical = 10.dp),
        style = Body05,
        text = text,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun WearListViewPreview() {
    WearListView(
        list = OuterWear.entries.map { it.displayName },
        selectedItem = OuterWear.Type1.displayName,
        onClickItem = {}
    )
}