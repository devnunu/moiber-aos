package co.kr.moiber.presentation.createmessage.firststep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.presentation.createmessage.CreateMessageState
import co.kr.moiber.presentation.createmessage.CreateMessageViewEvent
import co.kr.moiber.presentation.createmessage.components.WearListView
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.tab.MoiberTab
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body09
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.red01
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01

@Composable
fun Step1View(
    state: CreateMessageState,
    onEvent: (CreateMessageViewEvent) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column {
        Spacer(modifier = Modifier.size(38.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = Title03,
            text = "오늘 같은 날씨,\n무슨 옷을 입었나요?",
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(72.dp))
        Column(
            modifier = Modifier.padding(horizontal = 18.dp),
        ) {
            val list = listOf("상의", "하의", "외투")
            MoiberTab(
                tabList = list,
                selectedTabIndex = selectedTabIndex,
                onClickTab = { index -> selectedTabIndex = index }
            )
            when (selectedTabIndex) {
                0 -> {
                    WearListView(
                        list = UpperWear.entries.map { it.displayName },
                        selectedItem = state.upperWear?.displayName,
                        onClickItem = { displayName ->
                            val upperWear = UpperWear.entries.find { displayName == it.displayName }
                            onEvent(CreateMessageViewEvent.OnSelectUpperWear(upperWear))
                        }
                    )
                }

                1 -> {
                    WearListView(
                        list = BottomWear.entries.map { it.displayName },
                        selectedItem = state.bottomWear?.displayName,
                        onClickItem = { displayName ->
                            val bottomWear =
                                BottomWear.entries.find { displayName == it.displayName }
                            onEvent(CreateMessageViewEvent.OnSelectBottomWear(bottomWear))
                        }
                    )
                }

                2 -> {
                    WearListView(
                        list = OuterWear.entries.map { it.displayName },
                        selectedItem = state.outerWear?.displayName,
                        onClickItem = { displayName ->
                            val outerWear = OuterWear.entries.find { displayName == it.displayName }
                            onEvent(CreateMessageViewEvent.OnSelectOuterWear(outerWear))
                        }
                    )
                }
            }
            if (state.step1ErrorMsg != null) {
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    style = Body09,
                    text = state.step1ErrorMsg,
                    color = red01
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        MoiberButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 66.dp, start = 20.dp, end = 20.dp),
            enable = state.isStep1NextBtnEnable,
            backgroundColor = yellow01,
            fontColor = white01,
            fontStyle = Body04,
            buttonSize = ButtonSize.LARGE,
            shape = RoundedCornerShape(100.dp),
            text = "다음",
            onClick = { onEvent(CreateMessageViewEvent.OnClickStep1NextBtn) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Step1ViewPreview() {
    Step1View(
        state = CreateMessageState(),
        onEvent = {}
    )
}