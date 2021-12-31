package android.ptc.com.ptcflixing.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ActivitySplashBinding
import android.ptc.com.ptcflixing.ui.search.SearchActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launchWhenResumed { navigateToSearchScreen() }
    }

    private suspend fun navigateToSearchScreen() {
        delay(500)
        val intent = Intent(this, SearchActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this, binding.activitySplashLogo,
            getString(R.string.logo_transition_name)
        )
        startActivity(intent, options.toBundle())
        finish()
    }
}
