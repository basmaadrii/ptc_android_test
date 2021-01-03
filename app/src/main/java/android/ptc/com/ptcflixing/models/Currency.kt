package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class Currency (
        val iso: String,

        @Json(name = "currency_symbol")
        val currencySymbol: String,

        val position: Long,
        val decimals: Long,

        @Json(name = "thousands_sep")
        val thousandsSep: String,

        @Json(name = "decimals_sep")
        val decimalsSep: String
)