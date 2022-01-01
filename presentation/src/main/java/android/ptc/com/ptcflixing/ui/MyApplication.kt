package android.ptc.com.ptcflixing.ui

import android.app.Application
import android.ptc.com.ptcflixing.utils.SharedPreferenceManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferenceManager.setup(this)
    }
}
