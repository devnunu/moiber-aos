package co.kr.moiber.model.community

data class CommunityLike(
    val count: Int,
    val isMyLike: Boolean = false
)