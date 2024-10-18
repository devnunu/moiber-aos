package co.kr.moiber.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import co.kr.moiber.presentation.home.HomeScreen
import co.kr.moiber.presentation.report.ReportScreen
import co.kr.moiber.shared.ext.MoiberScreenAnim
import co.kr.moiber.shared.ext.moiberComposable

@Composable
fun MoiberNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home
    ) {
        moiberComposable<NavRoute.Home>(
            screenAnim = MoiberScreenAnim.FADE_IN_OUT
        ) {
            HomeScreen(
                navController = navController
            )
        }
        moiberComposable<NavRoute.Report>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE
        ) {
            ReportScreen(
                navController = navController
            )
        }
    }
}