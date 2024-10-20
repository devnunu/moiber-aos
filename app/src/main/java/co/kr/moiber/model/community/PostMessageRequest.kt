package co.kr.moiber.model.community

import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear

data class PostMessageRequest(
    val id: Long? = null,
    val userId: Long,
    val temperature: Int,
    val message: String? = null,
    val upperWear: UpperWear,
    val bottomWear: BottomWear,
    val outerWear: OuterWear? = null
)