package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityLike
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.CommunityVan
import co.kr.moiber.model.community.PostMessageRequest
import co.kr.moiber.model.community.ReportRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MemoryCommunityDataSourceImpl @Inject constructor() : MemoryCommunityDataSource {

    private val _communityMessageList = MutableStateFlow<List<CommunityMessage>>(listOf())

    override suspend fun getMessageList(): StateFlow<List<CommunityMessage>> = _communityMessageList

    override suspend fun saveMessageList(communityMessageList: List<CommunityMessage>) {
        _communityMessageList.update { communityMessageList }
    }

    override suspend fun postMessageLike(communityMessage: CommunityMessage) {
        val messageList = _communityMessageList.value.toMutableList()
        val index = messageList.indexOfFirst { it.id == communityMessage.id }
        if (index != -1) {
            val targetMessage = messageList[index]
            val targetLike = targetMessage.like ?: CommunityLike()
            val updatedLike = targetLike.copy(
                count = if (targetLike.isMyLike) targetLike.count - 1 else targetLike.count + 1,
                isMyLike = !targetLike.isMyLike
            )
            val updatedMessage = targetMessage.copy(like = updatedLike)
            messageList[index] = updatedMessage
        }
        _communityMessageList.update { messageList }
    }

    override suspend fun postMessageReport(
        communityMessage: CommunityMessage,
        reportRequest: ReportRequest
    ) {
        val messageList = _communityMessageList.value.toMutableList()
        val index = messageList.indexOfFirst { it.id == communityMessage.id }
        if (index != -1) {
            val targetMessage = messageList[index]
            val targetVan = targetMessage.van ?: CommunityVan()
            val updatedVan = targetVan.copy(count = targetVan.count + 1, isMyVan = true)
            val updatedMessage = targetMessage.copy(van = updatedVan)
            messageList[index] = updatedMessage
        }
        _communityMessageList.update { messageList }
    }

    override suspend fun deleteMessage(message: CommunityMessage) {
        val messageList = _communityMessageList.value.toMutableList()
        messageList.remove(message)
        _communityMessageList.update { messageList }
    }

    override suspend fun postMessage(postMessageRequest: PostMessageRequest): CommunityMessage {
        val communityMessageList = _communityMessageList.value.toMutableList()
        val lastId = communityMessageList[communityMessageList.lastIndex].id
        val newCommunityMessage = CommunityMessage(
            id = lastId + 1,
            userId = postMessageRequest.userId,
            upperWear = postMessageRequest.upperWear,
            bottomWear = postMessageRequest.bottomWear,
            outerWear = postMessageRequest.outerWear,
            text = postMessageRequest.message
        )
        communityMessageList.add(newCommunityMessage)
        _communityMessageList.update { communityMessageList }
        return newCommunityMessage
    }
}