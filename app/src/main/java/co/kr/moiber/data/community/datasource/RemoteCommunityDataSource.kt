package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage

interface RemoteCommunityDataSource {
    fun getCommunityMessageList(): List<CommunityMessage>?
    fun postMessageLike(communityMessage: CommunityMessage)
}