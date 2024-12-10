package co.kr.moiber.presentation.navigation

import co.kr.moiber.model.community.CommunityMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {

    /**
     * Intro
     * */
    @Serializable
    @SerialName("IntroLogin")
    data object IntroLogin : NavRoute

    @Serializable
    @SerialName("IntroNickName")
    data object IntroNickName : NavRoute

    @Serializable
    @SerialName("IntroTerms")
    data object IntroTerms : NavRoute

    @Serializable
    @SerialName("IntroLocationPermission")
    data object IntroLocationPermission : NavRoute

    @Serializable
    @SerialName("IntroWelcome")
    data object IntroWelcome : NavRoute

    /**
     * Home
     * */
    @Serializable
    @SerialName("Home")
    data object Home : NavRoute

    /**
     * Home
     * */
    @Serializable
    @SerialName("WeatherSelectLocation")
    data object WeatherSelectLocation : NavRoute

    @Serializable
    @SerialName("WeatherDetail")
    data object WeatherDetail : NavRoute

    /**
     * Community
     * */
    @Serializable
    @SerialName("Report")
    data object CommunityReport : NavRoute

    @Serializable
    @SerialName("CreateMessage")
    data class CommunityCreateMessage(val communityMessage: CommunityMessage?) : NavRoute
}