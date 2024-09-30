package co.kr.moiber.model.network

import android.net.http.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed class ResResult<out T> {

    data class Success<out T>(val data: T?) : ResResult<T>()
    data class Error(val error: Exception?) : ResResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Fail[code=$error]"
        }
    }
}

fun <T> Flow<T>.asResult(): Flow<ResResult<T>> {
    return this
        .map<T, ResResult<T>> { ResResult.Success(it) }
        .catch { e ->
            when (e) {
                is Exception -> emit(ResResult.Error(e))
                else -> e.printStackTrace()
            }
        }
}

suspend inline fun <T> ResResult<T?>.onSuccess(
    crossinline onResult: suspend ResResult.Success<T?>.(T?) -> Unit,
): ResResult<T?> {
    if (this is ResResult.Success) {
        this.data?.let {
            onResult(it)
        }
    }
    return this
}

suspend inline fun <T> ResResult<T>.onError(
    crossinline onResult: suspend ResResult.Error.(error: Exception?) -> Unit,
): ResResult<T> {
    if (this is ResResult.Error) {
        onResult(this.error)
    }
    return this
}

inline fun <T> ResResult<T>.onComplete(
    crossinline onResult: ResResult<*>.() -> Unit,
): ResResult<T> {
    onResult(this)
    return this
}

