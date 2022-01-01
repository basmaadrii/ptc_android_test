package android.ptc.com.ptcflixing.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <T> LiveData<Result<T>>.getSuccessEvent() =
    Transformations.switchMap(this) { result ->
        val singleLiveEvent = SingleLiveEvent<T>()
        if (result is Result.Success) {
            result.data?.let { singleLiveEvent.postValue(it) }
        }
        return@switchMap singleLiveEvent
    }

fun <T> LiveData<Result<T>>.getLoadingEvent() =
    Transformations.switchMap(this) { result ->
        val singleLiveEvent = SingleLiveEvent<Boolean>()
        singleLiveEvent.postValue(result is Result.Loading)
        return@switchMap singleLiveEvent
    }

fun <T> LiveData<Result<T>>.getErrorEvent() =
    Transformations.switchMap(this) { result ->
        val singleLiveEvent = SingleLiveEvent<String>()
        if (result is Result.Error) {
            singleLiveEvent.postValue(result.message)
        }
        return@switchMap singleLiveEvent
    }
