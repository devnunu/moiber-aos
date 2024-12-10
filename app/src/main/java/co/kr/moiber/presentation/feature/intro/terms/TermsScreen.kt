package co.kr.moiber.presentation.feature.intro.terms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.presentation.feature.intro.terms.components.TermsAllSelectBox
import co.kr.moiber.presentation.feature.intro.terms.components.TermsItem
import co.kr.moiber.presentation.navigation.NavRoute
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.ext.collectSideEffect
import co.kr.moiber.shared.ui.Body01
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01

@Composable
fun TermsScreen(
    viewModel: TermsViewModel = hiltViewModel(),
    navController: NavController
) {
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is TermsSideEffect.NavigateToLocationPermission -> {
                navController.navigate(NavRoute.IntroLocationPermission)
            }
        }
    }
    TermsScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun TermsScreen(
    state: TermsState,
    onEvent: (TermsViewEvent) -> Unit
) {
    MoiberScaffold(
        bottomBar = {
            MoiberButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 66.dp),
                shape = RoundedCornerShape(100.dp),
                backgroundColor = yellow01,
                fontColor = white01,
                fontStyle = Body04,
                buttonSize = ButtonSize.LARGE,
                enable = state.isAllSelected,
                text = "동의하고 시작하기",
                onClick = { onEvent(TermsViewEvent.OnClickBottomCta) }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.icn_back),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(34.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 9.dp),
                    style = Body01,
                    text = buildAnnotatedString {
                        append("모이버에 오신걸 환영해요!\n")
                        withStyle(style = Title03.copy(color = yellow01).toSpanStyle()) {
                            append("약관내용에 동의")
                        }
                        append("해주세요.")
                    },
                    color = black01
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.img_terms),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(21.dp))
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                TermsAllSelectBox(
                    isAllSelected = state.isAllSelected,
                    onClick = { onEvent(TermsViewEvent.OnClickAllSelectBox) }
                )
                state.termsList.forEach { terms ->
                    Spacer(modifier = Modifier.size(22.dp))
                    TermsItem(
                        modifier = Modifier.padding(horizontal = 11.dp),
                        terms = terms,
                        isSelected = state.selectedTermsList.contains(terms.termsId),
                        onClickItem = { onEvent(TermsViewEvent.OnClickTermsItem(terms.termsId)) }
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TermsScreenPreview() {
    TermsScreen(
        state = TermsState(),
        onEvent = {}
    )
}