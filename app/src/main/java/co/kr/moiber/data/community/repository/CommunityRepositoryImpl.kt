package co.kr.moiber.data.community.repository

import co.kr.moiber.data.community.datasource.MemoryCommunityDataSource
import co.kr.moiber.data.community.datasource.RemoteCommunityDataSource
import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.network.ResResult
import co.kr.moiber.model.network.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommunityRepositoryImpl @Inject constructor(
    private val remoteRemoteCommunityDataSource: RemoteCommunityDataSource,
    private val memoryMemoryCommunityDataSource: MemoryCommunityDataSource
) : CommunityRepository {

    override fun getCommunityContentList(forcedUpdate: Boolean): Flow<ResResult<List<CommunityContent>>> =
        memoryMemoryCommunityDataSource.getCommunityContentList().onStart {
            val cache = memoryMemoryCommunityDataSource.getCommunityContentList().value
            if (forcedUpdate || cache.isEmpty()) {
                remoteRemoteCommunityDataSource.getCommunityContentList()?.let {
                    memoryMemoryCommunityDataSource.saveCommunityContentList(it)
                }
            }
        }.mapNotNull { it }
            .asResult()

}