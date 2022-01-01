package android.ptc.com.ptcflixing.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val success: Boolean,
    val metadata: T? = null,
    val messages: Message? = null
)

@Serializable
data class Message(val error: ErrorMessage)

@Serializable
data class ErrorMessage(
    val reason: String,
    val message: String
)
