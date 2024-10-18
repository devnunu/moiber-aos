package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityLike
import co.kr.moiber.model.community.CommunityMessage
import co.kr.moiber.model.community.CommunityVan
import co.kr.moiber.model.community.ReportRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MemoryCommunityDataSourceImpl @Inject constructor() : MemoryCommunityDataSource {

    private val _communityMessageList = MutableStateFlow<List<CommunityMessage>>(listOf())

    override fun getMessageList(): StateFlow<List<CommunityMessage>> = _communityMessageList

    override fun saveMessageList(communityMessageList: List<CommunityMessage>) {
        _communityMessageList.update { communityMessageList }
    }

    override fun postMessageLike(communityMessage: CommunityMessage) {
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

    override fun postMessageReport(
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
}