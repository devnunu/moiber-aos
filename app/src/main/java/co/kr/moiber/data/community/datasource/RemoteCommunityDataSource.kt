package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.ReportRequest
import co.kr.moiber.model.network.ResResult
import kotlinx.coroutines.flow.Flow

interface RemoteCommunityDataSource {
    fun getCommunityMessageList(): List<CommunityMessage>?
    fun postMessageLike(communityMessage: CommunityMessage)
    fun postMessageReport(communityMessage: CommunityMessage, reportRequest: ReportRequest)
    suspend fun deleteMessage(message: CommunityMessage)
}