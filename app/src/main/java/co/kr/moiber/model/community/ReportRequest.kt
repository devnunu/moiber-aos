package co.kr.moiber.model.community

import co.kr.moiber.presentation.home.community.components.popup.ReportCase

data class ReportRequest(
    val reportCaseList: List<ReportCase> = emptyList(),
    val reportReason: String? = null,
)