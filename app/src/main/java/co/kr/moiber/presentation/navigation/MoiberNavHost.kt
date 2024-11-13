package co.kr.moiber.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.presentation.createmessage.CreateMessageScreen
import co.kr.moiber.presentation.createmessage.CreateMessageVariable.SUCCESS_MESSAGE_POST
import co.kr.moiber.presentation.home.HomeScreen
import co.kr.moiber.presentation.location.SelectLocationScreen
import co.kr.moiber.presentation.login.LoginScreen
import co.kr.moiber.presentation.nickname.NickNameScreen
import co.kr.moiber.presentation.report.ReportScreen
import co.kr.moiber.shared.ext.MoiberScreenAnim
import co.kr.moiber.shared.ext.moiberComposable
import kotlin.reflect.typeOf

@Composable
fun MoiberNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoute.Login
    ) {
        moiberComposable<NavRoute.Login>(
            screenAnim = MoiberScreenAnim.FADE_IN_OUT
        ) {
            LoginScreen(
                navController = navController
            )
        }

        moiberComposable<NavRoute.NickName>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE
        ) {
            NickNameScreen(
                navController = navController
            )
        }

        moiberComposable<NavRoute.Home>(
            screenAnim = MoiberScreenAnim.FADE_IN_OUT
        ) { backStackEntry ->
            val successMessagePost =
                backStackEntry.getResultAndClear<Boolean>(SUCCESS_MESSAGE_POST) ?: false

            HomeScreen(
                successMessagePost = successMessagePost,
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
        moiberComposable<NavRoute.SelectLocation>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE
        ) {
            SelectLocationScreen(
                navController = navController,
            )
        }
        moiberComposable<NavRoute.CreateMessage>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE,
            typeMap = mapOf(typeOf<CommunityMessage?>() to parcelableType<CommunityMessage?>(true))
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<NavRoute.CreateMessage>()
            CreateMessageScreen(
                args = args,
                navController = navController
            )
        }
    }
}