package android.ptc.com.ptcflixing.ui.fragments

import android.annotation.SuppressLint
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.FragmentSplashBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import android.ptc.com.ptcflixing.ui.common.BaseFragment
import android.ptc.com.ptcflixing.ui.utils.ViewUtils.toast
import android.ptc.com.ptcflixing.viewmodels.SplashViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FragmentSplash : BaseFragment<FragmentSplashBinding>() {

    override fun layout(): Int = R.layout.fragment_splash
    private val splashViewModel: SplashViewModel by viewModels()

    @SuppressLint("CheckResult")
    override fun onActivityCreated() {
        Observable.timer(2.5.toLong(), TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                view?.post {
                    if (splashViewModel.isConfigurationsSaved) {
                        toHome()
                    } else {
                        getConfigurations()
                    }
                }
            }
    }

    private fun getConfigurations() {
        splashViewModel.getConfigurations { success, error ->
            if (success) {
                toHome()
            } else {
                requireContext().toast(R.string.check_connection_try_again)
                requireActivity().finish()

            }
        }
    }

    private fun toHome() {
        findNavController().navigate(FragmentSplashDirections.actionFragmentSplashToFragmentSearch())

    }


}