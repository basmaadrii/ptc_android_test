package android.ptc.com.ptcflixing.api.models


import com.google.gson.annotations.SerializedName


data class Messages(
    @SerializedName("error")
    var error: Error?
) {

    data class Error(
        @SerializedName("message")
        var message: String?,
        @SerializedName("reason")
        var reason: String?
    )
}