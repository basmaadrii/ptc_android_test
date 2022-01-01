package android.ptc.com.ptcflixing.ui.product

import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.model.Image
import android.ptc.com.ptcflixing.databinding.ItemProductImageBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductImagesAdapter(
    private var images: List<Image>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<ProductImagesAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_image, parent, false))

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = images.size

    fun updateImages(images: List<Image>) {
        this.images = images
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProductImageBinding.bind(itemView)

        fun bind(position: Int) {
            val image = images[position]
            Glide.with(itemView.context).load(image.url).into(binding.itemProductImage)
            itemView.setOnClickListener { onItemClicked(position) }
            binding.itemProductImage.background = itemView.context.getDrawable(
                if (image.isSelected) R.drawable.ic_rectangle_orange else R.drawable.ic_rectangle_grey
            )
        }
    }
}