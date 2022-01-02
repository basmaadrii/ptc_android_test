package android.ptc.com.ptcflixing.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.ptc.com.ptcflixing.databinding.ActivitySearchBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[SearchViewModel::class.java]}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeListeners()
        setupSearchView()
        viewModel.setupConfiguration()
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