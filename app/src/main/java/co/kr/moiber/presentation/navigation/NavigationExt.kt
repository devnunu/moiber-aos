package co.kr.moiber.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import co.kr.moiber.presentation.createmessage.CreateMessageVariable

fun <T> NavBackStackEntry.getResultAndClear(key: String): T? {
    return try {
        @Suppress("UNCHECKED_CAST")
        val result = savedStateHandle.get(key) as T?
        savedStateHandle.remove<T>(key)
        result
    } catch (e: ClassCastException) {
        // Instead of failing on ClassCastException, we remove the value from the
        // SavedStateHandle and return null.
        savedStateHandle.remove<T>(key)
        null
    }
}

fun <T> NavController.setResult(key: String, value: T?) {
    previousBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)
}