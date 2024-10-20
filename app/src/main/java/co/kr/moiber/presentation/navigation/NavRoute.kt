package co.kr.moiber.presentation.navigation

import co.kr.moiber.model.community.CommunityMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {
    @Serializable
    @SerialName("Home")
    data object Home : NavRoute

    @Serializable
    @SerialName("Report")
    data object Report : NavRoute

    @Serializable
    @SerialName("CreateMessage")
    data class CreateMessage(val communityMessage: CommunityMessage?) : NavRoute
}