package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.FakeCommunityMessage
import co.kr.moiber.model.community.ReportRequest
import javax.inject.Inject

class RemoteCommunityDataSourceImpl @Inject constructor() : RemoteCommunityDataSource {

    override fun getCommunityMessageList(): List<CommunityMessage>? =
        FakeCommunityMessage.getFakeModelList()

    override fun postMessageLike(communityMessage: CommunityMessage) = Unit

    override fun postMessageReport(
        communityMessage: CommunityMessage,
        reportRequest: ReportRequest
    ) {
    }

    override suspend fun deleteMessage(message: CommunityMessage) {
    }
}