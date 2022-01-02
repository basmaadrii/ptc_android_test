package android.ptc.com.ptcflixing.data.utils

import android.content.Context
import android.content.SharedPreferences
import android.ptc.com.ptcflixing.data.model.Currency
import androidx.core.content.edit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

private const val CURRENCY = "currency"

object SharedPreferenceManager {
    private lateinit var sharedPreferences: SharedPreferences

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("Jumia PTC preferences", Context.MODE_PRIVATE)
    }

    fun clear() = sharedPreferences.edit { clear() }

    private fun getJsonDefaultConfig() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    var currency: Currency?
        get() = getString(CURRENCY)?.let { getJsonDefaultConfig().decodeFromJsonElement(getJsonDefaultConfig().parseToJsonElement(it)) }
        set(value) = setString(CURRENCY, Json.encodeToString(value))

    private fun getString(key: String): String? =
        if (sharedPreferences.contains(key)) sharedPreferences.getString(key, "") else null

    private fun setString(key: String, value: String) =
        sharedPreferences.edit { putString(key, value) }
}