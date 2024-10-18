package co.kr.moiber.data.community.repository

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.ReportRequest
import co.kr.moiber.model.network.ResResult
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    suspend fun getMessageList(forcedUpdate: Boolean): Flow<ResResult<List<CommunityMessage>>>

    suspend fun postMessageLike(message: CommunityMessage): Flow<ResResult<Unit>>

    suspend fun postMessageReport(
        message: CommunityMessage,
        reportRequest: ReportRequest
    ): Flow<ResResult<Unit>>

    suspend fun deleteMessage(message: CommunityMessage): Flow<ResResult<Unit>>
}