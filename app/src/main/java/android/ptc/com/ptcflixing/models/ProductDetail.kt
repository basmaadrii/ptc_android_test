package android.ptc.com.ptcflixing.models
import com.beust.klaxon.*

private val klaxon = Klaxon()

data class ProductDetail (
        val success: Boolean,
        @Json(name = "metadata")
        val metadata: MetadataProductDetail
) {
     fun toJson() = klaxon.toJsonString(this)

    companion object {
         fun fromJson(json: String) = klaxon.parse<ProductDetail>(json)
    }
}
