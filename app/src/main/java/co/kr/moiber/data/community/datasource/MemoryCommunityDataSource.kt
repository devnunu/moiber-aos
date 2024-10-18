package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.ReportRequest
import kotlinx.coroutines.flow.StateFlow

interface MemoryCommunityDataSource {

    fun getMessageList(): StateFlow<List<CommunityMessage>>

    fun saveMessageList(communityMessageList: List<CommunityMessage>)

    fun postMessageLike(communityMessage: CommunityMessage)

    fun postMessageReport(communityMessage: CommunityMessage, reportRequest: ReportRequest)
    suspend fun deleteMessage(message: CommunityMessage)
}