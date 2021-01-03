package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class SellerEntity (
        val id: Long,
        val name: String,

        @Json(name = "delivery_time")
        val deliveryTime: String
)