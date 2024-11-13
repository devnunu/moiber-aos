package co.kr.moiber.presentation.login

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import co.kr.moiber.shared.ext.clickableRipple
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.black02
import co.kr.moiber.shared.ui.white01

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            navController.navigate(NavRoute.Home)
        }
    }
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is LoginSideEffect.RequestPermission -> {
                checkLocationPermissionAndRequest(context, permissionLauncher) {
                    navController.navigate(NavRoute.Home)
                }
            }
        }
    }
    LoginScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onEvent: (LoginViewEvent) -> Unit
) {
    MoiberScaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
            ) {
                MoiberButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableRipple(onClick = {}, bounded = true),
                    backgroundColor = black02,
                    fontColor = white01,
                    fontStyle = Body04,
                    buttonSize = ButtonSize.LARGE,
                    text = "로그인",
                    onClick = { onEvent(LoginViewEvent.OnClickLoginBtn) }
                )
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(132.dp))
            Image(
                painter = painterResource(R.drawable.icn_logo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.back_and_login),
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
private fun LoginScreenPreview() {
    LoginScreen(
        state = LoginState(),
        onEvent = {}
    )
}