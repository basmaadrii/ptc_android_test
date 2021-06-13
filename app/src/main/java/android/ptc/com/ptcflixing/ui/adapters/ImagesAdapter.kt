package android.ptc.com.ptcflixing.ui.adapters

import android.annotation.SuppressLint
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ItemImageBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import android.ptc.com.ptcflixing.ui.common.DataBoundListAdapter


class ImagesAdapter(
    var onClicked: (String) -> Unit

) : DataBoundListAdapter<String, ItemImageBinding>
    (
    diffCallback = object : DiffUtil.ItemCallback<String>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ItemImageBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image,
            parent,
            false
        )
    }

    var selectedPosition = -1

    override fun bind(
        binding: ItemImageBinding,
        item: String,
        position: Int,
        adapterPosition: Int
    ) {
        binding.image = item
        if (selectedPosition == position)
            highlightSelectedItem(binding)
        else
            unHighlightItem(binding)

        binding.root.setOnClickListener {
            selectedPosition = position
            highlightSelectedItem(binding)
            onClicked.invoke(item)
            notifyDataSetChanged()
            notifyItemChanged(position)
        }
    }

    private fun highlightSelectedItem(binding: ItemImageBinding) {
        binding.root.setBackgroundDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                R.drawable.rounded_orange_stroke
            )
        )

    }

    private fun unHighlightItem(binding: ItemImageBinding) {
        binding.root.setBackgroundDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                R.drawable.rounded_grey_stroke
            )
        )
    }

}