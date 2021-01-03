package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class MetadataProductListing (
        val sort: String,

        @Json(name = "total_products")
        val totalProducts: Long,

        val title: String,
        val results: List<Result>
)