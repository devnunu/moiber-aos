package co.kr.moiber.model.community

import java.util.Date

data class CommunityContent(
    val id: Long,
    val feelGood: Boolean = true,
    val userId: Long,
    val userName: String? = null,
    val like: Int? = null,
    val location: String? = null,
    val message: String? = null,
    val outerwear: String? = null,
    val upperWear: String? = null,
    val bottomWear: String? = null,
    val insertTime: Date? = null
) {
    fun isMyContent(userId: Long) =
        this.userId == userId

}

object FakeCommunityContent {

    fun getFakeModel(
        id: Long = 0,
        feelGood: Boolean = true,
        userId: Long = 123,
        userName: String = "햅삐햅삐햅삐",
        message: String = "공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 공",
        outerwear: String = "가죽/레더 재킷",
        upperWear: String = "나시/민소매",
        bottomWear: String = "배기/조거팬츠",
        location: String = "성북구",
        insertTime: Date = Date()
    ) =
        CommunityContent(
            id = id,
            feelGood = feelGood,
            userId = userId,
            userName = userName,
            message = message,
            outerwear = outerwear,
            upperWear = upperWear,
            bottomWear = bottomWear,
            location = location,
            insertTime = insertTime,
        )

    fun getFakeModelList() =
        listOf(
            getFakeModel(),
            getFakeModel(
                userName = "쌀쌀 부추전",
                feelGood = false,
                message = "",
                outerwear = "가디건",
                upperWear = "반소매 티",
                bottomWear = "청바지",
            ),
            getFakeModel(
                userId = 0,
                message = "공백 포함 60자 이내만 작성 가능해요 공백 포함 60자 이내만 작성 가능해요 공백 포함 60자 이내만 작성"
            ),
            getFakeModel(
                userId = 0,
                feelGood = false,
                message = "",
                outerwear = "가디건",
                upperWear = "반소매 티",
                bottomWear = "청바지",
            ),
        )
}