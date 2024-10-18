package co.kr.moiber.presentation.createmessage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.kr.moiber.R
import co.kr.moiber.model.wear.BottomWear
import co.kr.moiber.model.wear.OuterWear
import co.kr.moiber.model.wear.UpperWear
import co.kr.moiber.presentation.createmessage.components.WearListView
import co.kr.moiber.presentation.createmessage.firststep.FirstStepView
import co.kr.moiber.presentation.home.HomeVariable
import co.kr.moiber.presentation.home.community.HomeCommunityViewEvent
import co.kr.moiber.shared.components.ButtonSize
import co.kr.moiber.shared.components.MoiberButton
import co.kr.moiber.shared.components.scaffold.MoiberScaffold
import co.kr.moiber.shared.components.tab.MoiberTab
import co.kr.moiber.shared.ui.Body04
import co.kr.moiber.shared.ui.Body07
import co.kr.moiber.shared.ui.Title03
import co.kr.moiber.shared.ui.black01
import co.kr.moiber.shared.ui.gray02
import co.kr.moiber.shared.ui.red01
import co.kr.moiber.shared.ui.white01
import co.kr.moiber.shared.ui.yellow01

object CreateMessageVariable {
    const val NUMBER_OF_PAGE = 3
}

@Composable
fun CreateMessageScreen(
    navController: NavController,
    viewModel: CreateMessageViewModel = hiltViewModel()
) {
    CreateMessageScreen(
        state = viewModel.stateFlow.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CreateMessageScreen(
    state: CreateMessageState,
    onEvent: (CreateMessageViewEvent) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { CreateMessageVariable.NUMBER_OF_PAGE })
    MoiberScaffold {
        Column {
            Spacer(modifier = Modifier.size(26.dp))
            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.icn_close),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))

            }
        }
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { index ->
            when (index) {
                0 -> {
                    FirstStepView(
                        state = state,
                        onEvent = onEvent
                    )
                }

                1 -> {

                }

                2 -> {

                }
            }
        }
    }
}