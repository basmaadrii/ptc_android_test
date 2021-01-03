package android.ptc.com.ptcflixing.models

import com.beust.klaxon.*

private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
        this.converter(object: Converter {
            @Suppress("UNCHECKED_CAST")
            override fun toJson(value: Any)        = toJson(value as T)
            override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
            override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
        })

private val klaxon = Klaxon()

data class ProductListing(
        val success: Boolean = false,
        @Json(name = "metadata")
        val metadata: MetadataProductListing? = null
) {
     fun toJson() = klaxon.toJsonString(this)

    companion object {
         fun fromJson(json: String) = klaxon.parse<ProductListing>(json)
    }
}
