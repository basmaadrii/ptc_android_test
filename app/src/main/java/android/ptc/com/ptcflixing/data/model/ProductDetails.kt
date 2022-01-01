package android.ptc.com.ptcflixing.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetails(
    val sku: String,
    val name: String,
    val brand: String,
    @SerialName("max_saving_percentage")
    val maxSavingPercentage: Int,
    val price: Float,
    @SerialName("special_price")
    val specialPrice: Float,
    val rating: Rating,
    @SerialName("image_list")
    val images: List<String>,
    val summary: Summary,
    @SerialName("seller_entity")
    val seller: Seller
)

@Serializable
data class Rating(
    val average: Float,
    @SerialName("ratings_total")
    val ratingsCount: Int
)

@Serializable
data class Summary(
    @SerialName("short_description")
    val shortDescription: String,
    val description: String
)

@Serializable
data class Seller(
    val id: Long,
    val name: String,
    @SerialName("delivery_time")
    val deliveryTime: String
)