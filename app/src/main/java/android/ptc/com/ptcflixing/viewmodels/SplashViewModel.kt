package android.ptc.com.ptcflixing.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.ptc.com.ptcflixing.repository.NetworkRepository
import android.ptc.com.ptcflixing.repository.SharedPreferenceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel(application: Application) :
    AndroidViewModel(application) {
    var preferenceRepository = SharedPreferenceRepository(application)
    var networkRepository = NetworkRepository()
    var isConfigurationsSaved: Boolean = preferenceRepository.getConfigurations() != null

    // Configurations
    @SuppressLint("CheckResult")
    fun getConfigurations(
        onFinish: (Boolean, String?) -> Unit
    ) {
        networkRepository.getConfigurations()
            .retry(3L)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())?.subscribe(
                { configs ->
                    onFinish.invoke(true, null)
                    preferenceRepository.saveConfigurations(configs)

                },
                {
                    onFinish.invoke(false, "unknown")
                }
            )
    }


}