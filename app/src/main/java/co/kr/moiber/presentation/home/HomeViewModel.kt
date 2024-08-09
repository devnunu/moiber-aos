package co.kr.moiber.presentation.home

import androidx.lifecycle.ViewModel
import co.kr.moiber.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeState, HomeViewEvent>(
    HomeState()
) {

}