package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityContent

interface RemoteCommunityDataSource {
    fun getCommunityContentList(): List<CommunityContent>?
}