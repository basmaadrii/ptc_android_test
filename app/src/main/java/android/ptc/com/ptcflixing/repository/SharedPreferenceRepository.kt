package android.ptc.com.ptcflixing.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.ptc.com.ptcflixing.api.models.ConfigurationsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceRepository(application: Application) {
    var preference: SharedPreferences = application.getSharedPreferences("", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveConfigurations(registerResponse: ConfigurationsResponse?) {
        preference.edit().putString("configs", gson.toJson(registerResponse)).apply()
    }

    fun getConfigurations(): ConfigurationsResponse? {
        val jsonPreferences = preference.getString("configs", null) ?: return null
        val type = object : TypeToken<ConfigurationsResponse>() {}.type
        return gson.fromJson<ConfigurationsResponse>(jsonPreferences, type)
    }



}