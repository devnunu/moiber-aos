package co.kr.moiber.presentation.navigation

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
    data object CreateMessage : NavRoute
}