package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import kotlinx.coroutines.flow.StateFlow

interface MemoryCommunityDataSource {

    fun getMessageList(): StateFlow<List<CommunityMessage>>

    fun saveMessageList(communityMessageList: List<CommunityMessage>)

    fun postMessageLike(communityMessage: CommunityMessage)
}