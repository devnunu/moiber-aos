package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MemoryCommunityDataSource {

    fun getCommunityContentList(): StateFlow<List<CommunityContent>>

    fun saveCommunityContentList(communityContentList: List<CommunityContent>)
}