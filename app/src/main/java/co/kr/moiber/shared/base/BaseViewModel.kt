package co.kr.moiber.shared.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<STATE : ViewState, VIEW_EVENT : ViewEvent>(
    initialState: STATE
): ViewModel() {
    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<STATE> = _stateFlow

    protected val state: STATE
    get() = _stateFlow.value

    private val _sideEffects = Channel<SideEffect>(capacity = Channel.BUFFERED)

    fun setState(reducer: STATE.() -> STATE) {
        _stateFlow.update(reducer)
    }

    fun postSideEffect(sideEffect: SideEffect) {
        _sideEffects.trySend(sideEffect)
    }

    abstract fun onEvent(event: VIEW_EVENT)
}