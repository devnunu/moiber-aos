package co.kr.moiber.presentation.feature.intro.locationpermission

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.presentation.navigation.NavRoute
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body08
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray01
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01

@Composable
fun LocationPermissionScreen(
    viewModel: LocationPermissionViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            navController.navigate(NavRoute.IntroWelcome)
        }
    }
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is LocationPermissionSideEffect.RequestPermission -> {
                checkLocationPermissionAndRequest(context, permissionLauncher) {
                    navController.navigate(NavRoute.IntroWelcome)
                }
            }
        }
    }
    LocationPermissionScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun LocationPermissionScreen(
    state: LocationPermissionState,
    onEvent: (LocationPermissionViewEvent) -> Unit
) {
    MoiberScaffold(
        bottomBar = {
            MoiberButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 66.dp),
                shape = RoundedCornerShape(100.dp),
                backgroundColor = white01,
                fontColor = yellow01,
                fontStyle = Body04,
                buttonSize = ButtonSize.LARGE,
                text = "다음",
                onClick = { onEvent(LocationPermissionViewEvent.OnClickNextBtn) }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.icn_close),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(26.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    style = Title03,
                    text = "모이버와 함께\n매일의 날씨를 공유해요!"
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    style = Body08,
                    text = buildAnnotatedString {
                        append("✔\uFE0F 해당 지역의 날씨 정보를 위해 ")
                        withStyle(style = Body08.copy(color = black01).toSpanStyle()) {
                            append("위치 동의")
                        }
                        append("가 필요해요.")
                    },
                    color = gray01
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    style = Body08,
                    text = buildAnnotatedString {
                        append("✔\uFE0F 날씨 및 서비스 알림을 받기 위해서 ")
                        withStyle(style = Body08.copy(color = black01).toSpanStyle()) {
                            append("알림 동의")
                        }
                        append("가 필요해요.")
                    },
                    color = gray01
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.back_and_accepticon),
                contentDescription = null
            )
        }
    }
}

private fun checkLocationPermissionAndRequest(
    context: android.content.Context,
    permissionLauncher: ActivityResultLauncher<String>,
    onAlreadyGranted: () -> Unit
) {
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) -> {
            onAlreadyGranted()
        }

        else -> {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocationPermissionScreenPreview() {
    LocationPermissionScreen(
        state = LocationPermissionState(),
        onEvent = {}
    )
}