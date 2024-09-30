package co.kr.moiber.shared.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<STATE : ViewState, VIEW_EVENT : ViewEvent, SIDE_EFFECT : SideEffect>(
    initialState: STATE
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<STATE> = _stateFlow

    protected val state: STATE
        get() = _stateFlow.value

    private val _sideEffects = MutableSharedFlow<SIDE_EFFECT>(replay = 0, extraBufferCapacity = 1)
    val sideEffect: Flow<SIDE_EFFECT> = _sideEffects

    fun setState(reducer: STATE.() -> STATE) {
        _stateFlow.update(reducer)
    }

    fun postSideEffect(sideEffect: SIDE_EFFECT) {
        _sideEffects.tryEmit(sideEffect)
    }

    abstract fun onEvent(event: VIEW_EVENT)
}