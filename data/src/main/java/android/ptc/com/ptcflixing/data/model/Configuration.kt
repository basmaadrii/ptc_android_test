package android.ptc.com.ptcflixing.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    val currency: Currency
)

@Serializable
data class Currency(
    val iso: String,
    @SerialName("currency_symbol")
    val currencySymbol: String,
    val position: Int,
    val decimals: Int,
    @SerialName("thousands_sep")
    val thousandSeparator: String,
    @SerialName("decimals_sep")
    val decimalSeparator: String
)
