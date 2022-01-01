package android.ptc.com.ptcflixing.common

import android.ptc.com.ptcflixing.utils.SharedPreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

fun <T> LiveData<Result<T>>.getSuccessEvent() =
    Transformations.switchMap(this) { result ->
        return@switchMap SingleLiveEvent<T>().also { event ->
            if (result is Result.Success) result.data?.let { event.postValue(it) }
        }
    }

fun <T> LiveData<Result<T>>.getLoadingEvent() =
    Transformations.switchMap(this) { result ->
        return@switchMap SingleLiveEvent<Boolean>().also {
            it.postValue(result is Result.Loading)
        }
    }

fun <T> LiveData<Result<T>>.getErrorEvent() =
    Transformations.switchMap(this) { result ->
        return@switchMap SingleLiveEvent<String>().also { event ->
            if (result is Result.Error) event.postValue(result.message)
        }
    }

fun Float?.toCurrencyFormat(): String {
    if (this == null) return ""
    val currency = SharedPreferenceManager.currency ?: return toString()
    val format = NumberFormat.getCurrencyInstance()
    val decimalFormatSymbols: DecimalFormatSymbols = (format as DecimalFormat).decimalFormatSymbols
    decimalFormatSymbols.currencySymbol = currency.currencySymbol
    format.decimalFormatSymbols = decimalFormatSymbols
    format.maximumFractionDigits = currency.decimals
    return format.format(this)
}
