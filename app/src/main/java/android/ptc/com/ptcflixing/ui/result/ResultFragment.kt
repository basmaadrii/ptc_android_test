package android.ptc.com.ptcflixing.ui.result

import android.os.Bundle
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.databinding.FragmentResultBinding
import android.ptc.com.ptcflixing.ui.main.DEFAULT_QUERY
import android.ptc.com.ptcflixing.ui.main.MainActivity
import android.ptc.com.ptcflixing.ui.main.QUERY_KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val PRODUCT_SKU_KEY = "product_sku"
private const val SPAN_COUNT = 2

@AndroidEntryPoint
class ResultFragment : Fragment() {
    private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ResultViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        search(arguments?.getString(QUERY_KEY, ""))
    }

    private fun setupRecyclerView() {
        binding.fragmentResultRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = ResultAdapter { product, selectedImage -> handleSelectedProduct(product, selectedImage) }
        }
    }

    private fun handleSelectedProduct(product: Product?, selectedImage: ImageView) {
        (requireActivity() as MainActivity).setToolbarTitle(product?.brand)
        ViewCompat.setTransitionName(selectedImage, "item_image")
        if (product == null) return
        val args = Bundle().apply { putString(PRODUCT_SKU_KEY, product.sku) }
        val extras = FragmentNavigatorExtras(selectedImage to "hero_image")
        findNavController().navigate(R.id.action_resultFragment_to_productFragment, args, null, extras)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            val pagingAdapter = binding.fragmentResultRecyclerView.adapter as ResultAdapter
            pagingAdapter.loadStateFlow.collectLatest { loadStates -> updateView(loadStates.refresh) }
        }
    }

    private fun updateView(loadState: LoadState) {
        binding.fragmentResultRecyclerView.isVisible = loadState is LoadState.NotLoading
        binding.fragmentResultErrorTv.isVisible = loadState is LoadState.Error
        binding.activtyResultProgressBar.isVisible = loadState is LoadState.Loading
        if (loadState is LoadState.Error) binding.fragmentResultErrorTv.text = loadState.error.message
    }

    private fun search(query: String?) {
        if (query == null) return
        Snackbar.make(requireContext(), binding.root, "Replacing received query \"$query\" with \"phone\"", Snackbar.LENGTH_LONG).show()
        (requireActivity() as MainActivity).setToolbarTitle(DEFAULT_QUERY)
        viewModel.search(DEFAULT_QUERY)
        viewModel.productLiveData.observe(viewLifecycleOwner, { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                val pagingAdapter = binding.fragmentResultRecyclerView.adapter as ResultAdapter
                pagingAdapter.submitData(pagingData)
            }
        })
    }
}