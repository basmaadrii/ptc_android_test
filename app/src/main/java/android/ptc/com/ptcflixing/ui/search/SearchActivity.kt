package android.ptc.com.ptcflixing.ui.search

import android.os.Bundle
import android.ptc.com.ptcflixing.databinding.ActivitySearchBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class SearchActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[SearchViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}