package android.ptc.com.ptcflixing.utils

import android.ptc.com.ptcflixing.data.model.Currency
import android.ptc.com.ptcflixing.data.utils.SharedPreferenceManager
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

fun Float?.toCurrencyFormat(currency: Currency?): String {
    if (this == null) return ""
    return currency?.let {
        val format = NumberFormat.getCurrencyInstance()
        (format as DecimalFormat).setSymbols(currency)
        format.maximumFractionDigits = currency.decimals
        format.format(this)
    } ?: toString()
}

private fun DecimalFormat.setSymbols(currency: Currency) {
    decimalFormatSymbols = decimalFormatSymbols.apply {
        currencySymbol = currency.currencySymbol
        decimalSeparator = currency.decimalSeparator[0]
        groupingSeparator = currency.thousandSeparator[0]
    }
}
