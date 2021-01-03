package android.ptc.com.ptcflixing.models

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Response (
        val success: Boolean,
        val messages: Messages
) {
    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Response>(json)
    }
}

data class Messages (
        val error: Error
)

data class Error (
        val reason: String,
        val message: String
)