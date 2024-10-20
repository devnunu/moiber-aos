package co.kr.moiber.model.community

import android.os.Parcelable
import co.kr.moiber.R
import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.shared.serializer.DateSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.Date

@Parcelize
@Serializable
data class CommunityMessage(
    val id: Long,
    val userId: Long,
    val userName: String? = null,
    val temperature: Int = 0,
    val like: CommunityLike? = null,
    val van: CommunityVan? = null,
    val location: String? = null,
    val text: String? = null,
    val outerWear: OuterWear? = null,
    val upperWear: UpperWear? = null,
    val bottomWear: BottomWear? = null,
    @Serializable(with = DateSerializer::class)
    val insertTime: Date? = null
) : Parcelable {
    fun isMyContent(userId: Long) =
        this.userId == userId

    val isVan: Boolean
        get() = (van?.count ?: 0) >= 5 || van?.isMyVan == true

    val tempImg: Int
        get() = when (temperature) {
            0 -> R.drawable.img_empty
            1 -> R.drawable.img_cold_l
            2 -> R.drawable.img_cool_l
            3 -> R.drawable.img_good_l
            4 -> R.drawable.img_muggy_l
            else -> R.drawable.img_hot_l
        }
}

object FakeCommunityMessage {

    fun getFakeModel(
        id: Long = 0,
        userId: Long = 123,
        like: CommunityLike? = null,
        van: CommunityVan? = null,
        userName: String = "햅삐햅삐햅삐",
        message: String = "공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 가능해요 공백 포함 45자 이내만 작성 공",
        outerwear: OuterWear = OuterWear.Type4,
        upperWear: UpperWear = UpperWear.Type1,
        bottomWear: BottomWear = BottomWear.Type4,
        temperature: Int = 3,
        location: String = "성북구",
        insertTime: Date = Date()
    ) =
        CommunityMessage(
            id = id,
            userId = userId,
            temperature = temperature,
            like = like,
            van = van,
            userName = userName,
            text = message,
            outerWear = outerwear,
            upperWear = upperWear,
            bottomWear = bottomWear,
            location = location,
            insertTime = insertTime,
        )

    fun getFakeModelList() =
        listOf(
            getFakeModel(
                id = 0,
                like = CommunityLike(count = 1),
                temperature = 1,
            ),
            getFakeModel(
                id = 1,
                like = CommunityLike(count = 2, isMyLike = true),
                userName = "쌀쌀 부추전",
                message = "",
                outerwear = OuterWear.Type2,
                upperWear = UpperWear.Type3,
                bottomWear = BottomWear.Type2,
                temperature = 2,
            ),
            getFakeModel(
                id = 2,
                userId = 0,
                message = "공백 포함 60자 이내만 작성 가능해요 공백 포함 60자 이내만 작성 가능해요 공백 포함 60자 이내만 작성",
            ),
            getFakeModel(
                id = 3,
                userId = 0,
                message = "",
                outerwear = OuterWear.Type5,
                upperWear = UpperWear.Type9,
                bottomWear = BottomWear.Type1,
                temperature = 4,
            ),
            getFakeModel(
                id = 4,
                userId = 0,
                van = CommunityVan(count = 5),
                message = "",
                outerwear = OuterWear.Type2,
                upperWear = UpperWear.Type1,
                bottomWear = BottomWear.Type3,
                temperature = 5,
            ),
            getFakeModel(
                id = 5,
                van = CommunityVan(count = 1, isMyVan = true),
                temperature = 6,
            ),
            getFakeModel(
                id = 6,
                van = CommunityVan(count = 5),
                userName = "쌀쌀 부추전",
                message = "",
                outerwear = OuterWear.Type7,
                upperWear = UpperWear.Type4,
                bottomWear = BottomWear.Type1,
            ),
        )
}