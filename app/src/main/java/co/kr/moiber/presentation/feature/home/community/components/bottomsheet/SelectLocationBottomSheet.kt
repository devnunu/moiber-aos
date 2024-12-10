package co.kr.moiber.presentation.feature.home.community.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.presentation.feature.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.ext.clickableNonIndication
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body05
import co.kr.moiber.shared.ui.Body06
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01
import co.kr.moiber.shared.ui.yellow02

@Composable
fun SelectLocationBottomSheet(
    onEvent: (HomeCommunityViewEvent) -> Unit
) {
    var selectedText by remember { mutableStateOf("모두") }
    Column(
        modifier = Modifier
            .background(white01)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.size(24.dp))
        Row {
            Spacer(Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickableNonIndication { onEvent(HomeCommunityViewEvent.OnCloseBottomSheet) },
                painter = painterResource(R.drawable.icn_close),
                contentDescription = null
            )
        }
        Spacer(Modifier.size(15.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = Title03,
            text = "지금, 이 동네 사람들은?!",
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = Body06,
            text = "궁금한 동네의 날씨 커뮤니티를 확인해보세요",
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(33.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LocationItem(
                    selectedText = selectedText,
                    text = "모두",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "서울",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "부산",
                    onClickText = { selectedText = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LocationItem(
                    selectedText = selectedText,
                    text = "대구",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "인천",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "광주",
                    onClickText = { selectedText = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LocationItem(
                    selectedText = selectedText,
                    text = "대전",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "울산",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "세종",
                    onClickText = { selectedText = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LocationItem(
                    selectedText = selectedText,
                    text = "경기",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "강원",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "충북",
                    onClickText = { selectedText = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LocationItem(
                    selectedText = selectedText,
                    text = "충남",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "전북",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "전남",
                    onClickText = { selectedText = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LocationItem(
                    selectedText = selectedText,
                    text = "경북",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "경남",
                    onClickText = { selectedText = it }
                )
                Spacer(Modifier.size(8.dp))
                LocationItem(
                    selectedText = selectedText,
                    text = "제주",
                    onClickText = { selectedText = it }
                )
            }
        }
        Spacer(Modifier.size(29.dp))
        MoiberButton(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = yellow01,
            fontColor = white01,
            fontStyle = Body04,
            buttonSize = ButtonSize.LARGE,
            shape = RoundedCornerShape(100.dp),
            text = "확인",
            onClick = { onEvent(HomeCommunityViewEvent.OnClickSelectLocationConfirmBtn(selectedText)) }
        )
        Spacer(Modifier.size(59.dp))
    }
}

@Composable
fun RowScope.LocationItem(
    selectedText: String,
    text: String,
    onClickText: (String) -> Unit
) {
    val isSelected = selectedText == text
    val borderWidth = if (isSelected) 2.dp else 1.dp
    val borderColor = if (isSelected) yellow01 else gray02
    val bgColor = if (isSelected) yellow02 else white01
    val style = if (isSelected) Body04 else Body05
    val textColor = if (isSelected) black01 else gray01
    Column(
        Modifier.weight(1f)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickableNonIndication {
                    onClickText(text)
                }
                .background(bgColor)
                .padding(vertical = 24.dp),
            style = style,
            text = text,
            textAlign = TextAlign.Center,
            color = textColor
        )
        Divider(
            thickness = borderWidth,
            color = borderColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectLocationBottomSheetPreview() {
    SelectLocationBottomSheet(
        onEvent = {}
    )
}