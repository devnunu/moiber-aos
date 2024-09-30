package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.network.ResResult
import kotlinx.coroutines.flow.Flow

interface CommunityDataSource {
    fun getCommunityContentList(): List<CommunityContent>?
}