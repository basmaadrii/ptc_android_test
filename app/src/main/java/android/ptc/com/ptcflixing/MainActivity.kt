package android.ptc.com.ptcflixing

import android.app.SearchManager
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.ptc.com.ptcflixing.fragments.ExplanationFragment
import android.ptc.com.ptcflixing.fragments.ProductListFragment
import android.ptc.com.ptcflixing.viewModels.ProductListViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ProductListViewModel
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        progressBar = findViewById(R.id.progress_bar)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ProductListViewModel::class.java)
        initializeObservers()
        showExplanation()
    }
    private fun showExplanation() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, ExplanationFragment(), "Explanation")
                .commit()
    }
    private fun initializeObservers(){
        viewModel.searchQuery.observe(this, Observer {
            CoroutineScope(Dispatchers.IO).launch {
                progressBar.visibility = View.VISIBLE
                viewModel.loadProducts(it)
            }
        })

        viewModel.getProducts().observe(this, Observer {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.root_layout, ProductListFragment.newInstance(), "ProductList")
                    .addToBackStack(null)
                    .commit()
            progressBar.visibility = View.GONE
        })
        viewModel.getError().observe(this, Observer {
            Toast.makeText(this@MainActivity,it, Toast.LENGTH_LONG).show()
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                searchView.clearFocus()
                searchItem.collapseActionView()
                if (p0.toLowerCase(Locale.ROOT) == "phone")
                    viewModel.searchQuery.value = p0
                else Toast.makeText(this@MainActivity,"Search for phone instead", Toast.LENGTH_LONG).show()
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}
