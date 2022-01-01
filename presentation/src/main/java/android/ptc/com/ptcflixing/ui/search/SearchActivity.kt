package android.ptc.com.ptcflixing.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.ptc.com.ptcflixing.databinding.ActivitySearchBinding
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeListeners()
        setupSearchView()
    }

    private fun initializeListeners() {
        binding.activitySearchButton.setOnClickListener {
            binding.activitySearchView.apply { setQuery(query, true) }
        }
    }

    private fun setupSearchView() {
        binding.activitySearchView.apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
        }
    }
}