package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class Support (
        @Json(name = "phone_number")
        val phoneNumber: String,

        @Json(name = "call_to_order_enabled")
        val callToOrderEnabled: Boolean,

        @Json(name = "cs_email")
        val csEmail: String
)
