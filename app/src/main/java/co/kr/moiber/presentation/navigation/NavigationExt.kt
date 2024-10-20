package co.kr.moiber.presentation.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import co.kr.moiber.presentation.createmessage.CreateMessageVariable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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

inline fun <reified T : Parcelable?> parcelableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putParcelable(key, value)

    override fun serializeAsValue(value: T): String = Uri.encode(json.encodeToString(value))

    override fun parseValue(value: String): T = json.decodeFromString<T>(value)
}