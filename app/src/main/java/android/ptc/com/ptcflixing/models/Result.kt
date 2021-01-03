package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class Result (
        val sku: String,
        val name: String,
        val brand: String,

        @Json(name = "max_saving_percentage")
        val maxSavingPercentage: Long,

        val price: Long,

        @Json(name = "special_price")
        val specialPrice: Long,

        val image: String,

        @Json(name = "rating_average")
        val ratingAverage: Long? = null
)