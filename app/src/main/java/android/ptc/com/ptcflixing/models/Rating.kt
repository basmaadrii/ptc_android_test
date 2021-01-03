package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class Rating (
        val average: Long,

        @Json(name = "ratings_total")
        val ratingsTotal: Long
)