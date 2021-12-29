package android.ptc.com.ptcflixing.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.ptc.com.ptcflixing.databinding.ActivitySplashBinding
import android.ptc.com.ptcflixing.ui.search.SearchActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launchWhenResumed {
            delay(500)
            val intent = Intent(this@SplashActivity, SearchActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity, binding.activitySplashLogo, "logo")
            startActivity(intent, options.toBundle())
        }
    }
}
