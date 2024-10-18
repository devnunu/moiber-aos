package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.FakeCommunityMessage
import javax.inject.Inject

class RemoteCommunityDataSourceImpl @Inject constructor() : RemoteCommunityDataSource {

    override fun getCommunityMessageList(): List<CommunityMessage>? =
        FakeCommunityMessage.getFakeModelList()

    override fun postMessageLike(communityMessage: CommunityMessage) = Unit
}