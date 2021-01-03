package android.ptc.com.ptcflixing.models

import com.beust.klaxon.Json

data class Session (
        val id: String,
        val expire: Any? = null,

        @Json(name = "YII_CSRF_TOKEN")
        val yiiCSRFToken: String
)