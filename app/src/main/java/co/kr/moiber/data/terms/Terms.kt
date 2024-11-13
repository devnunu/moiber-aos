package co.kr.moiber.data.terms

data class Terms(
    val termsId: Int,
    val title: String,
    val isRequired: Boolean = false,
    val content: String,
) {
}

object FakeTerms {
    fun getTermsList() = listOf(
        Terms(
            termsId = 0,
            title = "모이버 서비스 약관 동의",
            isRequired = true,
            content = "",
        ),
        Terms(
            termsId = 1,
            title = "개인정보 처리 방침 동의",
            isRequired = true,
            content = "",
        ),
        Terms(
            termsId = 2,
            title = "위치기반 기능 동의",
            isRequired = true,
            content = "",
        ),
    )
}