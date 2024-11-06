package co.kr.moiber.presentation.location.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.kr.moiber.R
import co.kr.moiber.model.weather.Weather
import co.kr.moiber.shared.ui.Body02
import co.kr.moiber.shared.ui.Title02
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.blue02
import co.kr.moiber.shared.ui.blue03
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow02
import co.kr.moiber.shared.ui.yellow03

@Composable
fun LocationInfoCard(
    locationText: String,
    isCurrentLocation: Boolean,
    isDay: Boolean,
    weather: Weather,
    temperature: Int
) {
    val weatherIconResId = when (weather) {
        Weather.SUNNY -> {
            if (isDay) R.drawable.icn_sun_l else R.drawable.icn_moon_l
        }

        Weather.SOME_CLOUDY -> {
            if (isDay) R.drawable.icn_cloud1_l else R.drawable.icn_cloud3_l
        }

        Weather.CLOUDY -> R.drawable.icn_cloud2_l
        Weather.RAINY -> R.drawable.icn_rain_l
        Weather.THUNDER -> R.drawable.icn_thunder_l
        else -> R.drawable.icn_snow_l
    }

    val bgColor = if (isDay) {
        if (isCurrentLocation) yellow02 else yellow03
    } else {
        if (isCurrentLocation) blue02 else blue03
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(10.dp))
            .padding(vertical = 13.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val textColor = if (isDay) black01 else white01
        Text(
            style = Body02,
            text = locationText,
            color = textColor
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(weatherIconResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = Title02,
            text = "${temperature}°",
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LocationInfoCardPreview1() {
    LocationInfoCard(
        locationText = "서울 특별시 성북구",
        isCurrentLocation = true,
        isDay = true,
        weather = Weather.SUNNY,
        temperature = 20
    )
}

@Preview(showBackground = true)
@Composable
fun LocationInfoCardPreview2() {
    LocationInfoCard(
        locationText = "서울 특별시 성북구",
        isCurrentLocation = false,
        isDay = false,
        weather = Weather.SUNNY,
        temperature = 23
    )
}