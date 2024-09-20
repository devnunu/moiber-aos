package co.kr.moiber.presentation.home.summary.components.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable

@Composable
fun HomeAnimationVisibility(
    isSlide: Boolean = false,
    visible: Boolean,
    duration: Int,
    delay: Int,
    content: @Composable () -> Unit
) {
    var animation: EnterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = duration, // 1초 동안 애니메이션
            delayMillis = delay        // 1초 지연 후 애니메이션 시작
        )
    )
    AnimatedVisibility(
        visible = visible,
        enter = if (isSlide) {
            animation + slideInVertically(
                initialOffsetY = { -40 }, // -40dp 위에서 시작
                animationSpec = tween(
                    durationMillis = duration, // 1초 동안 애니메이션
                    delayMillis = delay        // 1초 지연 후 애니메이션 시작
                )
            )
        } else {
            animation
        }
    ) {
        content()
    }
}