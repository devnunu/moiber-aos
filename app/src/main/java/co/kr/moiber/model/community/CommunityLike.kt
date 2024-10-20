package co.kr.moiber.model.community

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CommunityLike(
    val count: Int = 0,
    val isMyLike: Boolean = false
) : Parcelable