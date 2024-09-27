package co.kr.moiber.model.community

import java.util.Date

data class CommunityContent(
    val id: Long,
    val feelGood: Boolean = true,
    val userId: Long,
    val userName: String? = null,
    val location: String? = null,
    val message: String? = null,
    val outerwear: String? = null,
    val upperWear: String? = null,
    val bottomWear: String? = null,
    val insertTime: Date? = null
)

object FakeCommunityContent {

    fun getFakeModel() =
        CommunityContent(
            id = 0,
            userId = 123,
            userName = "햅삐햅삐햅삐",
            message = "공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 공",
            outerwear = "가죽/레더 재킷",
            upperWear = "나시/민소매",
            bottomWear = "배기/조거팬츠",
            location = "성북구",
            insertTime = Date()
        )

    fun getFakeModelList() =
        listOf(
            getFakeModel(),
            getFakeModel(),
            getFakeModel(),
            getFakeModel(),
            getFakeModel(),
        )
}