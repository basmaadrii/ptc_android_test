package android.ptc.com.ptcflixing.ui.fragments

import android.ptc.com.ptcflixing.R
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import android.ptc.com.ptcflixing.api.models.ProductsResponse
import android.ptc.com.ptcflixing.databinding.FragmentProductsBinding
import android.ptc.com.ptcflixing.ui.adapters.ProductsPagingAdapter
import android.ptc.com.ptcflixing.ui.common.BaseFragment

class FragmentProducts : BaseFragment<FragmentProductsBinding>() {

    override fun layout(): Int = R.layout.fragment_products

    override fun onActivityCreated() {
        binding.mainViewModel = mainViewModel
        arguments.let {
            if (!it!!.isEmpty) {
                val query = FragmentProductsArgs.fromBundle(it).query
                mainViewModel.toolbarData.handleNormal(query)
                getProducts(query)
            }
        }

    }

    private fun getProducts(query: String) {
        mainViewModel.retryProducts(query)
        mainViewModel.productsLiveData.observe(
            viewLifecycleOwner,
            { products ->
                if (products != null) {
                    initProductsRecycler(products)
                }
            })
    }

    private fun initProductsRecycler(products: PagedList<ProductsResponse.Result>) {
        val productsAdapter = ProductsPagingAdapter(
            this.requireContext(),
            mainViewModel.networkStatusMutableLiveData,
            mainViewModel.getCurrency()
        ) {
            toProductDetails(it.sku)
        }
        addRecyclerDecoration()
        binding.productsRV.adapter = productsAdapter
        productsAdapter.submitList(products)


    }

    private fun toProductDetails(sku: String?) {
        findNavController().navigate(
            FragmentProductsDirections.actionFragmentProductsToFragmentProductDetails(
                sku!!
            )
        )
    }

    private fun addRecyclerDecoration() {
        binding.productsRV.setHasFixedSize(true)
        binding.productsRV.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.productsRV.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}