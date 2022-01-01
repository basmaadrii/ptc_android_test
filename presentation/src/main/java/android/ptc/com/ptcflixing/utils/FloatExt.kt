package android.ptc.com.ptcflixing.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

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