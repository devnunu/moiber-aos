package co.kr.moiber.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.presentation.feature.community.createmessage.CommunityCreateMessageScreen
import co.kr.moiber.presentation.feature.community.createmessage.CreateMessageVariable.SUCCESS_MESSAGE_POST
import co.kr.moiber.presentation.feature.home.HomeScreen
import co.kr.moiber.presentation.feature.weather.location.WeatherSelectLocationScreen
import co.kr.moiber.presentation.feature.intro.login.LoginScreen
import co.kr.moiber.presentation.feature.intro.nickname.NickNameScreen
import co.kr.moiber.presentation.feature.community.report.CommunityReportScreen
import co.kr.moiber.presentation.feature.intro.locationpermission.LocationPermissionScreen
import co.kr.moiber.presentation.feature.intro.terms.TermsScreen
import co.kr.moiber.presentation.feature.intro.welcome.WelcomeScreen
import co.kr.moiber.presentation.feature.mypage.MyPageScreen
import co.kr.moiber.presentation.feature.weather.detail.WeatherDetailScreen
import co.kr.moiber.shared.ext.MoiberScreenAnim
import co.kr.moiber.shared.ext.moiberComposable
import kotlin.reflect.typeOf

@Composable
fun MoiberNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoute.IntroLogin
    ) {

        /**
         * Intro
         * */
        moiberComposable<NavRoute.IntroLogin>(
            screenAnim = MoiberScreenAnim.FADE_IN_OUT
        ) {
            LoginScreen(
                navController = navController
            )
        }

        moiberComposable<NavRoute.IntroNickName>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE
        ) {
            NickNameScreen(
                navController = navController
            )
        }

        moiberComposable<NavRoute.IntroTerms>(
            screenAnim = MoiberScreenAnim.HORIZONTAL_SLIDE
        ) {
            TermsScreen(
                navController = navController
            )
        }

        moiberComposable<NavRoute.IntroLocationPermission>(
            screenAnim = MoiberScreenAnim.HORIZONTAL_SLIDE
        ) {
            LocationPermissionScreen(
                navController = navController
            )
        }

        moiberComposable<NavRoute.IntroWelcome>(
            screenAnim = MoiberScreenAnim.HORIZONTAL_SLIDE
        ) {
            WelcomeScreen(
                navController = navController
            )
        }

        /**
         * Home
         * */
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

        /**
         * MyPage
         * */
        moiberComposable<NavRoute.MyPage>(
            screenAnim = MoiberScreenAnim.FADE_IN_OUT
        ) {
            MyPageScreen(
                navController = navController
            )
        }

        /**
         * Weather
         * */
        moiberComposable<NavRoute.WeatherSelectLocation>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE
        ) {
            WeatherSelectLocationScreen(
                navController = navController,
            )
        }

        moiberComposable<NavRoute.WeatherDetail>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE
        ) {
            WeatherDetailScreen(
                navController = navController,
            )
        }

        /**
         * Community
         * */
        moiberComposable<NavRoute.CommunityReport>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE
        ) {
            CommunityReportScreen(
                navController = navController
            )
        }

        moiberComposable<NavRoute.CommunityCreateMessage>(
            screenAnim = MoiberScreenAnim.VERTICAL_SLIDE,
            typeMap = mapOf(typeOf<CommunityMessage?>() to parcelableType<CommunityMessage?>(true))
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<NavRoute.CommunityCreateMessage>()
            CommunityCreateMessageScreen(
                args = args,
                navController = navController
            )
        }
    }
}