package android.ptc.com.ptcflixing.ui.result

import android.graphics.Paint
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.databinding.ItemResultBinding
import android.ptc.com.ptcflixing.utils.toCurrencyFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ResultAdapter(
    val onItemClicked: ((Product?, selectedImage: ImageView) -> Unit)
) : PagingDataAdapter<Product, ResultAdapter.ResultViewHolder>(ProductComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false))

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemResultBinding.bind(itemView)

        fun bind(position: Int) {
            val product = getItem(position)
            Glide.with(itemView.context).load(product?.image ?: "").into(binding.itemResultImage)
            itemView.setOnClickListener { onItemClicked(product, binding.itemResultImage) }
            updateView(product)
            binding.itemResultPriceTv.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
        }

        private fun updateView(product: Product?) = binding.apply {
            itemResultBrandTv.text = product?.brand ?: ""
            itemResultNameTv.text = product?.name ?: ""
            itemResultPriceTv.text = product?.price?.toCurrencyFormat()
            itemResultSpecialPriceTv.text = product?.specialPrice?.toCurrencyFormat()
            val discount = product?.let { "-${product.maxSavingPercentage}%" } ?: ""
            itemResultDiscountTv.text = discount
            itemResultRatingBar.rating = product?.ratingAverage ?: 0F
        }
    }

    object ProductComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.sku == newItem.sku
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

}