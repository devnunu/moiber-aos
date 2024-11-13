package co.kr.moiber.presentation.navigation

import co.kr.moiber.model.community.CommunityMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {

    @Serializable
    @SerialName("Login")
    data object Login : NavRoute

    @Serializable
    @SerialName("NickName")
    data object NickName : NavRoute

    @Serializable
    @SerialName("Terms")
    data object Terms : NavRoute

    @Serializable
    @SerialName("LocationPermission")
    data object LocationPermission : NavRoute

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