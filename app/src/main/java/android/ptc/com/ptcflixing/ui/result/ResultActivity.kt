package android.ptc.com.ptcflixing.ui.result

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.os.Bundle
import android.ptc.com.ptcflixing.databinding.ActivityResultBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val SPAN_COUNT = 2
private const val DEFAULT_QUERY = "phone"

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ResultViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.activityResultToolbar)
        handleIntent(intent)
        setupSearchView()
        setupRecyclerView()
        setListeners()
        setupObservers()
    }

    private fun handleIntent(intent: Intent) {
        if (intent.action != ACTION_SEARCH) return
        intent.getStringExtra(SearchManager.QUERY)?.also { query -> search(query) }
    }

    private fun search(query: String) {
        Snackbar.make(this, binding.root, "Replacing received query \"$query\" with \"phone\"", Snackbar.LENGTH_LONG).show()
        binding.activityResultTitleTv.text = DEFAULT_QUERY
        viewModel.search(DEFAULT_QUERY)
        viewModel.productLiveData.observe(this, { pagingData ->
            lifecycleScope.launch {
                val pagingAdapter = binding.activityResultRecyclerView.adapter as ResultAdapter
                pagingAdapter.submitData(pagingData)
            }
        })
    }

    private fun setupRecyclerView() {
        binding.activityResultRecyclerView.apply {
            layoutManager = GridLayoutManager(this@ResultActivity, SPAN_COUNT)
            addItemDecoration(DividerItemDecoration(this@ResultActivity, DividerItemDecoration.HORIZONTAL))
            addItemDecoration(DividerItemDecoration(this@ResultActivity, DividerItemDecoration.VERTICAL))
            adapter = ResultAdapter {}
        }
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

    private fun setListeners() {
        binding.activityResultBackBtn.setOnClickListener { onBackPressed() }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            val pagingAdapter = binding.activityResultRecyclerView.adapter as ResultAdapter
            pagingAdapter.loadStateFlow.collectLatest { loadStates -> updateView(loadStates.refresh) }
        }
    }

    private fun updateView(loadState: LoadState) {
        binding.activityResultRecyclerView.isVisible = loadState is LoadState.NotLoading
        binding.activityResultErrorTv.isVisible = loadState is LoadState.Error
        binding.activtyResultProgressBar.isVisible = loadState is LoadState.Loading
        if (loadState is LoadState.Error) binding.activityResultErrorTv.text = loadState.error.message
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }
}
