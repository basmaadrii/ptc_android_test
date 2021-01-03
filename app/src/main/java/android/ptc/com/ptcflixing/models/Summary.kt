package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class Summary (
        @Json(name = "short_description")
        val shortDescription: String,

        val description: String
)
