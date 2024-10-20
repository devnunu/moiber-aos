package co.kr.moiber.data.community.repository

import co.kr.moiber.data.community.datasource.MemoryCommunityDataSource
import co.kr.moiber.data.community.datasource.RemoteCommunityDataSource
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.PostMessageRequest
import co.kr.moiber.model.community.ReportRequest
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

    override suspend fun getMessageList(forcedUpdate: Boolean): Flow<ResResult<List<CommunityMessage>>> =
        memoryMemoryCommunityDataSource.getMessageList().onStart {
            val cache = memoryMemoryCommunityDataSource.getMessageList().value
            if (forcedUpdate || cache.isEmpty()) {
                remoteRemoteCommunityDataSource.getCommunityMessageList()?.let {
                    memoryMemoryCommunityDataSource.saveMessageList(it)
                }
            }
        }.mapNotNull { it }
            .asResult()

    override suspend fun postMessageLike(message: CommunityMessage): Flow<ResResult<Unit>> = flow {
        // 추후 memory datasource 코드는 삭제, 결과값을 local에 업데이트 하도록 로직 변경
        remoteRemoteCommunityDataSource.postMessageLike(communityMessage = message)
        memoryMemoryCommunityDataSource.postMessageLike(communityMessage = message)
        emit(Unit)
    }.asResult()

    override suspend fun postMessageReport(
        message: CommunityMessage,
        reportRequest: ReportRequest
    ): Flow<ResResult<Unit>> = flow {
        // 추후 memory datasource 코드는 삭제, 결과값을 local에 업데이트 하도록 로직 변경
        remoteRemoteCommunityDataSource.postMessageReport(
            communityMessage = message,
            reportRequest = reportRequest
        )
        memoryMemoryCommunityDataSource.postMessageReport(
            communityMessage = message,
            reportRequest = reportRequest
        )
        emit(Unit)
    }.asResult()

    override suspend fun deleteMessage(message: CommunityMessage): Flow<ResResult<Unit>> = flow {
        // 추후 memory datasource 코드는 삭제, 결과값을 local에 업데이트 하도록 로직 변경
        remoteRemoteCommunityDataSource.deleteMessage(message = message)
        memoryMemoryCommunityDataSource.deleteMessage(message = message)
        emit(Unit)
    }.asResult()

    override suspend fun postMessage(postMessageRequest: PostMessageRequest): Flow<ResResult<CommunityMessage>> =
        flow {
            remoteRemoteCommunityDataSource.postMessage(postMessageRequest)
            val result = memoryMemoryCommunityDataSource.postMessage(postMessageRequest)
            emit(result)
        }.asResult()

    override suspend fun modifyMessage(postMessageRequest: PostMessageRequest): Flow<ResResult<CommunityMessage?>> =
        flow {
            remoteRemoteCommunityDataSource.modifyMessage(postMessageRequest)
            val result = memoryMemoryCommunityDataSource.modifyMessage(postMessageRequest)
            emit(result)
        }.asResult()
}