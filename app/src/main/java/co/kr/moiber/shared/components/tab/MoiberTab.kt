package co.kr.moiber.shared.components.tab

import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01

@Composable
fun MoiberTab(
    tabList: List<String>,
    selectedTabIndex: Int,
    onClickTab: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = white01,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = black01
                )
            }
        },
        divider = { Divider(thickness = 3.dp, color = gray02) },
    ) {
        tabList.forEachIndexed { index, item ->
            val isSelected = selectedTabIndex == index
            Tab(
                text = {
                    Text(
                        style = Body04,
                        text = item,
                        color = if (isSelected) black01 else gray02
                    )
                },
                selected = isSelected,
                onClick = { onClickTab(index) }
            )
        }
    }
}