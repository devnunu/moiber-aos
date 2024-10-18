package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MemoryCommunityDataSourceImpl @Inject constructor() : MemoryCommunityDataSource {

    private val _communityContentList = MutableStateFlow<List<CommunityContent>>(listOf())

    override fun getCommunityContentList(): StateFlow<List<CommunityContent>> = _communityContentList

    override fun saveCommunityContentList(communityContentList: List<CommunityContent>) {
        _communityContentList.update { communityContentList }
    }
}