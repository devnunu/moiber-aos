package co.kr.moiber.data.community.repository

import co.kr.moiber.data.community.datasource.CommunityDataSource
import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.network.ResResult
import co.kr.moiber.model.network.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommunityRepositoryImpl @Inject constructor(
    private val remoteCommunityDataSource: CommunityDataSource
) : CommunityRepository {

    override fun getCommunityContentList(): Flow<ResResult<List<CommunityContent>>> = flow {
        val result = remoteCommunityDataSource.getCommunityContentList()
        emit(result)
    }.mapNotNull { it }
        .asResult()

}