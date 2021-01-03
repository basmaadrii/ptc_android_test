package android.ptc.com.ptcflixing.ui

import android.content.Context
import android.graphics.Paint
import android.ptc.com.ptcflixing.databinding.ProductListItemBinding
import android.ptc.com.ptcflixing.models.Configurations
import android.ptc.com.ptcflixing.models.Result
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class ProductListViewHolder(itemBinding: ProductListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
    private val binding : ProductListItemBinding = itemBinding

    fun bindView(product: Result, onClickListener: OnClickListener) {
        binding.product = product
        binding.executePendingBindings()
        val image = binding.imageProduct
        itemView.setOnClickListener{
            onClickListener.onClick(product.sku)
        }
        val sharedPref = itemView.context.getSharedPreferences(itemView.context.packageName, Context.MODE_PRIVATE) ?: return
        val configurations = sharedPref.getString(itemView.context.packageName + "_configurations", "")?.let { Configurations.fromJson(it) }

        val format = NumberFormat.getCurrencyInstance(Locale("en-NG"))
        format.currency = Currency.getInstance(configurations?.metadata?.currency?.iso)

        val productPrice = binding.textProductPrice
        productPrice.text = format.format(product.price).toString()
        productPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        val productSpecialPrice = binding.textProductSpecialPrice
        productSpecialPrice.text = format.format(product.specialPrice).toString()
        val productDiscount = binding.textProductDiscount
        productDiscount.text = "- " + product.maxSavingPercentage.toString()+"%"
        Glide.with(image.context).load(product.image).fitCenter().into(image)
        val rating = binding.rating
        product.ratingAverage?.let{
            rating.rating = it.toFloat()
        }
    }
}