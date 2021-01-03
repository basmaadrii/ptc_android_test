package android.ptc.com.ptcflixing.fragments

import android.content.Context
import android.os.Bundle
import android.ptc.com.ptcflixing.MainActivity
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ProductListFragmentBinding
import android.ptc.com.ptcflixing.ui.OnClickListener
import android.ptc.com.ptcflixing.ui.ProductListAdapter
import android.ptc.com.ptcflixing.viewModels.ProductListViewModel
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager

class ProductListFragment : Fragment(), OnClickListener {

    companion object {
        fun newInstance() = ProductListFragment()
    }
    private lateinit var viewModel: ProductListViewModel
    private lateinit var binding: ProductListFragmentBinding
    private var flag: Boolean = false

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = ProductListFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider((activity as MainActivity), ViewModelProvider.NewInstanceFactory()).get(ProductListViewModel::class.java)
        val activity = activity as Context
        (activity as MainActivity).supportActionBar?.title = viewModel.searchQuery.value
        val recyclerView = this.binding.recyclerView

        viewModel.getProducts().observe(viewLifecycleOwner, Observer { products ->
            recyclerView.adapter = products?.let {
                ProductListAdapter(it, this)
            }
        })
        viewModel.getProduct().observe(viewLifecycleOwner, Observer {
            if (flag) {
                val detailsFragment = ProductDetailFragment.newInstance()
                activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.root_layout, detailsFragment, "ProductDetail")
                        .addToBackStack(null)
                        .commit()
                flag = false
            }
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(activity,
                DividerItemDecoration.HORIZONTAL))
        recyclerView.addItemDecoration(DividerItemDecoration(activity,
                DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = GridLayoutManager(activity, 2)

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.search).isVisible = false
    }

    override fun onClick(sku: String) {
        flag = false
        viewModel.loadProductDetail(sku)
        flag = true
    }
}

