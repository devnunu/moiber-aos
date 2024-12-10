package co.kr.moiber.presentation.navigation

import co.kr.moiber.model.community.CommunityMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {

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

    @Serializable
    @SerialName("Home")
    data object Home : NavRoute

    @Serializable
    @SerialName("Report")
    data object Report : NavRoute

    @Serializable
    @SerialName("SelectLocation")
    data object SelectLocation : NavRoute

    @Serializable
    @SerialName("CreateMessage")
    data class CreateMessage(val communityMessage: CommunityMessage?) : NavRoute
}