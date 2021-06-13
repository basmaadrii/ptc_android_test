package android.ptc.com.ptcflixing.api.models


import com.google.gson.annotations.SerializedName


data class ProductsResponse(
    @SerializedName("results")
    var results: List<Result?>?,
    @SerializedName("sort")
    var sort: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("total_products")
    var totalProducts: Int?
) {

    data class Result(
        @SerializedName("brand")
        var brand: String?,
        @SerializedName("image")
        var image: String?,
        @SerializedName("max_saving_percentage")
        var maxSavingPercentage: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("price")
        var price: Int?,
        @SerializedName("rating_average")
        var ratingAverage: Int?,
        @SerializedName("sku")
        var sku: String?,
        @SerializedName("special_price")
        var specialPrice: Int?
    )
}