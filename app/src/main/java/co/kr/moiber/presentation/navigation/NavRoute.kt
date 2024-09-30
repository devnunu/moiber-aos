package co.kr.moiber.presentation.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {
    @Serializable
    @SerialName("Home")
    object Home : NavRoute

    @Serializable
    @SerialName("Report")
    object Report : NavRoute
}