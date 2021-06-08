package android.ptc.com.ptcflixing.ui.fragments

import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.api.models.BaseResponse
import android.ptc.com.ptcflixing.api.models.ProductDetailsResponse
import android.ptc.com.ptcflixing.databinding.FragmentProductDetailsBinding
import android.ptc.com.ptcflixing.ui.adapters.ImagesAdapter
import android.ptc.com.ptcflixing.ui.common.BaseFragment
import android.ptc.com.ptcflixing.ui.utils.ViewUtils

class FragmentProductDetails : BaseFragment<FragmentProductDetailsBinding>() {

    override fun layout(): Int = R.layout.fragment_product_details
    override fun onActivityCreated() {
        mainViewModel.toolbarData.handleNormal(getString(R.string.product_details))
        binding.mainViewModel = mainViewModel
        arguments.let {
            if (!it!!.isEmpty) {
                val productId = FragmentProductDetailsArgs.fromBundle(it).productId
                getProductDetails(productId)
            }
        }
    }

    private fun getProductDetails(productId: String) {
        mainViewModel.getProductDetails(requireContext(), productId)
        { success, error ->
            mainViewModel.productDetailsLoadingState.loadedState()
            if (success) {
                initProductDetailsObserver()
            } else {
                handleVariantErrors(error)
            }
        }
    }

    private fun initProductDetailsObserver() {
        mainViewModel.productDetailsMutableLiveData.observe(
            viewLifecycleOwner
        ) { productDetails ->
            if (productDetails != null) {
                initView(productDetails)
                initImagesRecycler(productDetails.metadata?.imageList)
                setHeaderImage(productDetails.metadata?.imageList?.get(0))
            }
        }
    }

    private fun initView(productDetails: BaseResponse<ProductDetailsResponse>) {
        binding.product = productDetails.metadata
        binding.currency = mainViewModel.getCurrency()
        ViewUtils.span(
            binding.productAfterDiscountTV,
            productDetails.metadata?.price?.toFloat()?.toInt().toString(),
            mainViewModel.getCurrency()
        )
    }

    private fun handleVariantErrors(error: String?) {
        if (error.isNullOrBlank()) {
            mainViewModel.productDetailsLoadingState.errorState(getString(R.string.check_connection_try_again))
        } else {
            mainViewModel.productDetailsLoadingState.errorState(error)
        }
    }


    private fun initImagesRecycler(imageList: List<String?>?) {
        val adapter = ImagesAdapter { image ->
            setHeaderImage(image)
        }
        binding.imagesRV.adapter = adapter
        adapter.submitList(imageList)
    }

    private fun setHeaderImage(image: String?) {
        binding.image = image
    }


}
