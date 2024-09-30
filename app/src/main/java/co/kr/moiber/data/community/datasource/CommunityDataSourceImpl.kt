package co.kr.moiber.data.community.datasource

import co.kr.moiber.model.community.CommunityContent
import co.kr.moiber.model.community.FakeCommunityContent
import javax.inject.Inject

class CommunityDataSourceImpl @Inject constructor() : CommunityDataSource {

    override fun getCommunityContentList(): List<CommunityContent>? =
        FakeCommunityContent.getFakeModelList()
}