package android.ptc.com.ptcflixing.api.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("metadata")
    var metadata: T?,
    @SerializedName("success")
    var success: Boolean?,
    @SerializedName("messages")
    var messages: Messages?
)
