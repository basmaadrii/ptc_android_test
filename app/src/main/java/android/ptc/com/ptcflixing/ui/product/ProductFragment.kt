package android.ptc.com.ptcflixing.ui.product

import android.graphics.Paint
import android.os.Bundle
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.common.toCurrencyFormat
import android.ptc.com.ptcflixing.data.model.Image
import android.ptc.com.ptcflixing.data.model.ProductDetails
import android.ptc.com.ptcflixing.databinding.FragmentProductBinding
import android.ptc.com.ptcflixing.ui.result.PRODUCT_SKU_KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ProductViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
        postponeEnterTransition()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ViewCompat.setTransitionName(binding.fragmentProductMainImage, "hero_image")
        setRecyclerView()
        setObservers()
        viewModel.getProduct(arguments?.getString(PRODUCT_SKU_KEY))
    }

    private fun setRecyclerView() {
        binding.fragmentProductImagesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = ProductImagesAdapter(listOf()) { position -> viewModel.changeImageSelected(position) }
        }
    }

    private fun setObservers() {
        viewModel.productDetailsLoadingEvent.observe(viewLifecycleOwner, { showLoading(it) })
        viewModel.productDetailsErrorEvent.observe(viewLifecycleOwner, { showError(it) })
        viewModel.productDetailsSuccessEvent.observe(viewLifecycleOwner, { updateView(it) })
        viewModel.imagesLiveData.observe(viewLifecycleOwner, { showImages(it) })
    }

    private fun showLoading(show: Boolean) {
        binding.fragmentProductProgressBar.isVisible = show
        binding.fragmentProductErrorTv.isVisible = !show
        showData(!show)
    }

    private fun showError(message: String) {
        binding.fragmentProductErrorTv.isVisible = true
        binding.fragmentProductProgressBar.isVisible = false
        binding.fragmentProductErrorTv.text = message
        showData(false)
    }

    private fun showData(show: Boolean) {
        startPostponedEnterTransition()
        binding.fragmentProductNestedScrollView.isVisible = show
        binding.fragmentProductShareBtn.isVisible = show
        binding.fragmentProductBuyNowTv.isVisible = show
    }

    private fun updateView(product: ProductDetails) {
        binding.fragmentProductProgressBar.isVisible = false
        binding.fragmentProductErrorTv.isVisible = false

        Glide.with(requireContext()).load(product.images[0]).into(binding.fragmentProductMainImage)
        startPostponedEnterTransition()
        binding.fragmentProductPriceTv.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
        binding.fragmentProductPriceTv.text = product.price.toCurrencyFormat()
        binding.fragmentProductSpecialPriceTv.text = product.specialPrice.toCurrencyFormat()
        val discount = "-${product.maxSavingPercentage}%"
        binding.fragmentProductDiscountTv.text = discount
        binding.fragmentProductRatingBar.rating = product.rating.average
        val ratingCount = "${product.rating.ratingsCount} ratings"
        binding.fragmentProductRatingCountTv.text = ratingCount
        binding.fragmentProductSpecificationsContent.text = product.summary.shortDescription
        binding.fragmentProductDescriptionContent.text = product.summary.description
        val sellerInformation = "${product.seller.name}: ${product.seller.deliveryTime}"
        binding.fragmentProductSellerContent.text = sellerInformation
    }

    private fun showImages(images: List<Image>) {
        (binding.fragmentProductImagesRecyclerView.adapter as ProductImagesAdapter).updateImages(images)
        Glide.with(requireContext()).load(images.find { it.isSelected }?.url).into(binding.fragmentProductMainImage)
    }
}