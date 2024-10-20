package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.PostMessageRequest
import co.kr.moiber.model.community.ReportRequest
import kotlinx.coroutines.flow.StateFlow

interface MemoryCommunityDataSource {

    suspend fun getMessageList(): StateFlow<List<CommunityMessage>>
    suspend fun saveMessageList(communityMessageList: List<CommunityMessage>)
    suspend fun postMessageLike(communityMessage: CommunityMessage)
    suspend fun postMessageReport(communityMessage: CommunityMessage, reportRequest: ReportRequest)
    suspend fun deleteMessage(message: CommunityMessage)
    suspend fun postMessage(postMessageRequest: PostMessageRequest): CommunityMessage
    suspend fun modifyMessage(postMessageRequest: PostMessageRequest): CommunityMessage?
}