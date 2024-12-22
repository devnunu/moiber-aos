package co.kr.moiber.presentation.feature.weather.detail.components.dayview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.kr.moiber.model.weather.WeatherByDate
import co.kr.moiber.shared.ext.getDayDiff
import co.kr.moiber.shared.ext.toFormatString
import co.kr.moiber.shared.ext.toKoreanWeekday
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01
import co.kr.moiber.shared.ui.yellow03
import java.util.Date

@Composable
fun WeatherDateViewItem(
    weatherByDate: WeatherByDate,
) {
    val dayDiff = Date().getDayDiff(weatherByDate.date ?: Date())
    val dateText = when (dayDiff) {
        0L -> "오늘"
        1L -> "내일"
        else -> weatherByDate.date?.toKoreanWeekday() ?: "-"
    }
    val isToday = dayDiff == 0L
    val bgColor = if (isToday) yellow03 else white01
    val fontStyle = if (isToday) Body07 else Body08
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(4.dp))
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(26.dp),
            style = fontStyle,
            text = dateText,
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            modifier = Modifier.width(33.dp),
            style = fontStyle,
            text = weatherByDate.date?.toFormatString("MM.dd") ?: "-",
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(weatherByDate.weatherIconResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            modifier = Modifier.width(23.dp),
            style = fontStyle,
            text = "${weatherByDate.minTemp}°",
            color = black01,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(10.dp))
        val lineColor = if(isToday) yellow01 else gray02
        Box(
            modifier = Modifier.widthIn(min = 8.dp, max = 92.dp)
                .width(92.dp)
                .height(3.dp)
                .background(lineColor, RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.width(23.dp),
            style = fontStyle,
            text = "${weatherByDate.maxTemp}°",
            color = black01,
            textAlign = TextAlign.Center
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun WeatherDateViewItemPreview() {
//    WeatherDateViewItem(
//        weatherByDate = ()
//    )
//}