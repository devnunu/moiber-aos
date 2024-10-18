package co.kr.moiber.model.community

import java.util.Date

data class CommunityMessage(
    val id: Long,
    val feelGood: Boolean = true,
    val userId: Long,
    val userName: String? = null,
    val like: CommunityLike? = null,
    val van: CommunityVan? = null,
    val location: String? = null,
    val text: String? = null,
    val outerwear: String? = null,
    val upperWear: String? = null,
    val bottomWear: String? = null,
    val insertTime: Date? = null
) {
    fun isMyContent(userId: Long) =
        this.userId == userId

    val isVan: Boolean
        get() = (van?.count ?: 0) >= 5 || van?.isMyVan == true
}

object FakeCommunityMessage {

    fun getFakeModel(
        id: Long = 0,
        feelGood: Boolean = true,
        userId: Long = 123,
        like: CommunityLike? = null,
        van: CommunityVan? = null,
        userName: String = "햅삐햅삐햅삐",
        message: String = "공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 공",
        outerwear: String = "가죽/레더 재킷",
        upperWear: String = "나시/민소매",
        bottomWear: String = "배기/조거팬츠",
        location: String = "성북구",
        insertTime: Date = Date()
    ) =
        CommunityMessage(
            id = id,
            feelGood = feelGood,
            userId = userId,
            like = like,
            van = van,
            userName = userName,
            text = message,
            outerwear = outerwear,
            upperWear = upperWear,
            bottomWear = bottomWear,
            location = location,
            insertTime = insertTime,
        )

    fun getFakeModelList() =
        listOf(
            getFakeModel(
                id = 0,
                like = CommunityLike(count = 1)
            ),
            getFakeModel(
                id = 1,
                userName = "쌀쌀 부추전",
                feelGood = false,
                like = CommunityLike(count = 2, isMyLike = true),
                message = "",
                outerwear = "가디건",
                upperWear = "반소매 티",
                bottomWear = "청바지",
            ),
            getFakeModel(
                id = 2,
                userId = 0,
                message = "공백 포함 60자 이내만 작성 가능해요 공백 포함 60자 이내만 작성 가능해요 공백 포함 60자 이내만 작성"
            ),
            getFakeModel(
                id = 3,
                userId = 0,
                feelGood = false,
                message = "",
                outerwear = "가디건",
                upperWear = "반소매 티",
                bottomWear = "청바지",
            ),
            getFakeModel(
                id = 4,
                userId = 0,
                feelGood = false,
                van = CommunityVan(count = 5),
                message = "",
                outerwear = "가디건",
                upperWear = "반소매 티",
                bottomWear = "청바지",
            ),
            getFakeModel(
                id = 5,
                van = CommunityVan(count = 1, isMyVan = true)
            ),
            getFakeModel(
                id = 6,
                userName = "쌀쌀 부추전",
                feelGood = false,
                van = CommunityVan(count = 5),
                message = "",
                outerwear = "가디건",
                upperWear = "반소매 티",
                bottomWear = "청바지",
            ),
        )
}