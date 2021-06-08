package android.ptc.com.ptcflixing.ui.adapters

import android.content.Context
import android.ptc.com.ptcflixing.R
import androidx.recyclerview.widget.DiffUtil
import android.ptc.com.ptcflixing.api.models.ProductsResponse
import android.ptc.com.ptcflixing.databinding.ItemProductBinding
import android.ptc.com.ptcflixing.ui.common.BaseBindingPagingAdapter
import android.ptc.com.ptcflixing.ui.utils.NetworkState
import android.ptc.com.ptcflixing.ui.utils.SingleLiveEvent
import android.ptc.com.ptcflixing.ui.utils.ViewUtils

class ProductsPagingAdapter(
    context: Context,
    networkStatusMutable: SingleLiveEvent<NetworkState>,
    var currency: String?,
    onclick: (ProductsResponse.Result) -> Unit
) : BaseBindingPagingAdapter<ProductsResponse.Result, ItemProductBinding>(
    context,
    networkStatusMutable,
    R.layout.item_product,
    onItemClick = onclick,
    diffCallback = object :
        DiffUtil.ItemCallback<ProductsResponse.Result>() {
        override fun areContentsTheSame(
            oldItem: ProductsResponse.Result,
            newItem: ProductsResponse.Result
        ): Boolean {
            return oldItem.sku == newItem.sku
        }

        override fun areItemsTheSame(
            oldItem: ProductsResponse.Result,
            newItem: ProductsResponse.Result
        ): Boolean {
            return oldItem.sku == newItem.sku
        }

    }
) {
    override fun bind(
        binding: ItemProductBinding,
        item: ProductsResponse.Result,
        adapterPosition: Int
    ) {
        binding.item = item
        binding.currency = currency
        ViewUtils.span(
            binding.productAfterDiscountTV,
            item.price?.toFloat()?.toInt().toString(),
            currency
        )


    }

}