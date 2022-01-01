package android.ptc.com.ptcflixing.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.os.Bundle
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

const val DEFAULT_QUERY = "phone"
const val QUERY_KEY = "query_key"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.activityResultToolbar)
        handleIntent(intent)
        setupSearchView()
        setListeners()
    }

    private fun handleIntent(intent: Intent) {
        if (intent.action != ACTION_SEARCH) return
        intent.getStringExtra(SearchManager.QUERY)?.also { query ->
            binding.activityResultTitleTv.text = DEFAULT_QUERY
            navigateToResultFragment(query)
        }
    }

    private fun navigateToResultFragment(query: String) {
        val navController = supportFragmentManager.findFragmentById(R.id.activity_result_nav_host_fragment)?.findNavController() ?: return
        val args = Bundle().apply { putString(QUERY_KEY, query) }
        navController.setGraph(R.navigation.nav_graph, args)
    }

    private fun setListeners() {
        binding.activityResultBackBtn.setOnClickListener { onBackPressed() }
    }

    private fun setupSearchView() {
        binding.activityResultSearchView.apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnSearchClickListener { binding.activityResultTitleTv.isVisible = false }
            setOnCloseListener {
                binding.activityResultTitleTv.isVisible = true
                false
            }
        }
    }

    fun setToolbarTitle(title: String?) {
        title?.let { binding.activityResultTitleTv.text = title }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }
}
