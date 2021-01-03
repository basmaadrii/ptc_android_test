package android.ptc.com.ptcflixing.ui

import android.ptc.com.ptcflixing.databinding.ProductListItemBinding
import android.ptc.com.ptcflixing.models.Result
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductListAdapter(private val products: List<Result>, var itemClick: OnClickListener) : RecyclerView.Adapter<ProductListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductListItemBinding = ProductListItemBinding.inflate(layoutInflater,parent,false)
        return ProductListViewHolder(
                binding
        )
    }
    override fun getItemCount(): Int {
        return products.size
    }
    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = products[position]
        holder.bindView(product, itemClick)
    }
}
interface OnClickListener{
    fun onClick(sku: String)
}