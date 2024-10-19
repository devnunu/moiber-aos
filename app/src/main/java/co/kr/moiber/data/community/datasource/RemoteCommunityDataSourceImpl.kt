package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.FakeCommunityMessage
import co.kr.moiber.model.community.PostMessageRequest
import co.kr.moiber.model.community.ReportRequest
import co.kr.moiber.model.network.ResResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteCommunityDataSourceImpl @Inject constructor() : RemoteCommunityDataSource {

    override suspend fun getCommunityMessageList(): List<CommunityMessage>? =
        FakeCommunityMessage.getFakeModelList()

    override suspend fun postMessageLike(communityMessage: CommunityMessage) = Unit

    override suspend fun postMessageReport(
        communityMessage: CommunityMessage,
        reportRequest: ReportRequest
    ) {
    }

    override suspend fun deleteMessage(message: CommunityMessage) {
    }

    override suspend fun postMessage(postMessageRequest: PostMessageRequest): CommunityMessage =
        CommunityMessage(userId = 0, id = 0)
}