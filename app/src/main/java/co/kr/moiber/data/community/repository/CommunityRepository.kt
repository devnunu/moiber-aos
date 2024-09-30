package co.kr.moiber.data.community.repository

import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.network.ResResult
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    fun getCommunityContentList(): Flow<ResResult<List<CommunityContent>>>
}