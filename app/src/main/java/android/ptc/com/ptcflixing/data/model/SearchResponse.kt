package android.ptc.com.ptcflixing.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val sort: String,
    @SerialName("total_products")
    val totalProducts: Int,
    val title: String,
    val results: List<Product>
)
