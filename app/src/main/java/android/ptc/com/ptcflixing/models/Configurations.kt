package android.ptc.com.ptcflixing.models

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Configurations(
        val success: Boolean,
        val session: Session,
        val metadata: Metadata
) {
     fun toJson() = klaxon.toJsonString(this)

    companion object {
         fun fromJson(json: String) = klaxon.parse<Configurations>(json)
         fun toJson() = klaxon.toJsonString(this)
    }
}

data class Metadata (
        val currency: Currency,
        val languages: List<Language>,
        val support: Support
)

