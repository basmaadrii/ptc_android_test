package android.ptc.com.ptcflixing.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val sku: String,
    val name: String,
    val brand: String,
    @SerialName("max_saving_percentage")
    val maxSavingPercentage: Int,
    val price: Float,
    @SerialName("special_price")
    val specialPrice: Float,
    val image: String,
    @SerialName("rating_average")
    val ratingAverage: Float = 0F
)
