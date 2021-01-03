package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class MetadataProductDetail (
        val sku: String,
        val name: String,

        @Json(name = "max_saving_percentage")
        val maxSavingPercentage: Long,

        val price: Long,

        @Json(name = "special_price")
        val specialPrice: Long,

        val brand: String,
        val rating: Rating,

        @Json(name = "image_list")
        val imageList: List<String>,

        val summary: Summary,

        @Json(name = "seller_entity")
        val sellerEntity: SellerEntity
)