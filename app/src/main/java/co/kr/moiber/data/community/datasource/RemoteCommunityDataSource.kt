package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.ReportRequest

interface RemoteCommunityDataSource {
    fun getCommunityMessageList(): List<CommunityMessage>?
    fun postMessageLike(communityMessage: CommunityMessage)
    fun postMessageReport(communityMessage: CommunityMessage, reportRequest: ReportRequest)
}