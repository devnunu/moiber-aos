package co.kr.moiber.shared.ext

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import co.kr.moiber.shared.base.BaseViewModel
import co.kr.moiber.shared.base.SideEffect
import co.kr.moiber.shared.base.ViewEvent
import co.kr.moiber.shared.base.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import kotlin.reflect.KType

private const val DEFAULT_THROTTLE_DURATION = 300L

fun Modifier.fadingEdge(): Modifier = this.then(
    Modifier
        .graphicsLayer { alpha = 0.99F }
        .drawWithContent {
            drawContent()
            val bottomColors = listOf(Color.Black, Color.Transparent)
            val bottomEndY = size.height
            val bottomGradientHeight = 93.dp.toPx()
            drawRect(
                brush = Brush.verticalGradient(
                    colors = bottomColors,
                    startY = bottomEndY - bottomGradientHeight,
                    endY = bottomEndY
                ),
                blendMode = BlendMode.DstIn
            )
        }
)

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.combinedClickableRipple(
    bounded: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
): Modifier = composed {
    this.combinedClickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(bounded = bounded),
        onClick = onClick,
        onLongClick = onLongClick,
    )
}

fun Modifier.clickableRipple(
    bounded: Boolean = false,
    throttleDuration: Long = DEFAULT_THROTTLE_DURATION,
    onClick: () -> Unit,
): Modifier = composed {
    var lastEventMilli by remember { mutableStateOf(0L) }
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(bounded = bounded),
        onClick = {
            val now = System.currentTimeMillis()
            if (now - lastEventMilli >= throttleDuration) {
                lastEventMilli = now
                onClick()
            }
        }
    )
}

fun Modifier.clickableNonIndication(
    duration: Long = DEFAULT_THROTTLE_DURATION,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: () -> Unit
): Modifier = composed {
    var lastEventMilli by remember { mutableStateOf(0L) }
    this.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = {
            val now = System.currentTimeMillis()
            if (now - lastEventMilli >= duration) {
                lastEventMilli = now
                onClick()
            }
        }
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun <STATE : ViewState, VIEW_EVENT : ViewEvent, SIDE_EFFECT : SideEffect> BaseViewModel<STATE, VIEW_EVENT, SIDE_EFFECT>.collectSideEffect(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    sideEffect: (suspend (sideEffect: SIDE_EFFECT) -> Unit)? = null
) {
    val sideEffectFlow: Flow<SIDE_EFFECT?> = this.sideEffect
    val lifecycleOwner = LocalLifecycleOwner.current

    val callback by rememberUpdatedState(newValue = sideEffect)

    LaunchedEffect(sideEffectFlow, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            launch {
                sideEffectFlow.mapNotNull { it }.collect { callback?.invoke(it) }
            }
        }
    }
}

enum class MoiberScreenAnim {
    HORIZONTAL_SLIDE,
    VERTICAL_SLIDE,
    FADE_IN_OUT,
}

inline fun <reified T : Any> NavGraphBuilder.moiberComposable(
    screenAnim: MoiberScreenAnim = MoiberScreenAnim.HORIZONTAL_SLIDE,
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable<T>(
        enterTransition = {
            when (screenAnim) {
                MoiberScreenAnim.HORIZONTAL_SLIDE -> {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(durationMillis = 400)
                    )
                }

                MoiberScreenAnim.VERTICAL_SLIDE -> {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(durationMillis = 400)
                    )
                }

                MoiberScreenAnim.FADE_IN_OUT -> fadeIn()
            }
        },
        exitTransition = {
            when (screenAnim) {
                MoiberScreenAnim.HORIZONTAL_SLIDE -> null
                MoiberScreenAnim.VERTICAL_SLIDE -> null
                MoiberScreenAnim.FADE_IN_OUT -> fadeOut()
            }
        },
        popEnterTransition = {
            when (screenAnim) {
                MoiberScreenAnim.HORIZONTAL_SLIDE -> null
                MoiberScreenAnim.VERTICAL_SLIDE -> null
                MoiberScreenAnim.FADE_IN_OUT -> fadeIn()
            }
        },
        popExitTransition = {
            when (screenAnim) {
                MoiberScreenAnim.HORIZONTAL_SLIDE -> {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(durationMillis = 400)
                    )
                }

                MoiberScreenAnim.VERTICAL_SLIDE -> {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(durationMillis = 400)
                    )
                }

                MoiberScreenAnim.FADE_IN_OUT -> {
                    fadeOut()
                }
            }
        },
        typeMap = typeMap,
        content = content
    )
}

// popBackStack 시에 recomposable로 인해 LaunchedEffect가 한번더 동작하는것을 방지하기 위해 사용
// navigate로 페이지에 다시 진입하면 정상적으로 계속 호출됨, popBackStack로 복귀했을때만 동작하지 않도록 처리되는것을 확인
@Composable
fun LaunchedEffectOnce(
    content: suspend CoroutineScope.() -> Unit
) {
    var hasLaunched by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!hasLaunched) {
            content()
            hasLaunched = true
        }
    }
}