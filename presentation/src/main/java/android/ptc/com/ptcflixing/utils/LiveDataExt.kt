package android.ptc.com.ptcflixing.utils

import android.ptc.com.ptcflixing.common.Result
import android.ptc.com.ptcflixing.common.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <T> LiveData<Result<T>>.getSuccessEvent(): LiveData<T> =
    Transformations.switchMap(this) { result ->
        return@switchMap SingleLiveEvent<T>().also { event ->
            if (result is Result.Success) result.data?.let { event.postValue(it) }
        }
    }

fun <T> LiveData<Result<T>>.getLoadingEvent(): LiveData<Boolean> =
    Transformations.switchMap(this) { result ->
        return@switchMap SingleLiveEvent<Boolean>().also {
            it.postValue(result is Result.Loading)
        }
    }

fun <T> LiveData<Result<T>>.getErrorEvent(): LiveData<String> =
    Transformations.switchMap(this) { result ->
        return@switchMap SingleLiveEvent<String>().also { event ->
            if (result is Result.Error) event.postValue(result.message)
        }
    }
