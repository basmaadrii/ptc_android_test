package android.ptc.com.ptcflixing.api.models

import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("brand")
    var brand: String?,
    @SerializedName("image_list")
    var imageList: List<String?>?,
    @SerializedName("max_saving_percentage")
    var maxSavingPercentage: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("rating")
    var rating: Rating?,
    @SerializedName("seller_entity")
    var sellerEntity: SellerEntity?,
    @SerializedName("sku")
    var sku: String?,
    @SerializedName("special_price")
    var specialPrice: Int?,
    @SerializedName("summary")
    var summary: Summary?
) {

    data class Rating(
        @SerializedName("average")
        var average: Int?,
        @SerializedName("ratings_total")
        var ratingsTotal: Int?
    )


    data class SellerEntity(
        @SerializedName("delivery_time")
        var deliveryTime: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?
    )


    data class Summary(
        @SerializedName("description")
        var description: String?,
        @SerializedName("short_description")
        var shortDescription: String?
    )
}