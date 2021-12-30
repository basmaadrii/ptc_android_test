package android.ptc.com.ptcflixing.ui.result

import android.graphics.Paint
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ItemResultBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ResultAdapter(
    var searchItems: List<String>,
    val onItemClicked: ((String) -> Unit)
) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false))

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = searchItems.size

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemResultBinding.bind(itemView)

        fun bind(position: Int) {
            val item = searchItems[position]
            Glide.with(itemView.context).load(item).into(binding.itemResultImage)
            binding.itemResultPriceTv.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
            itemView.setOnClickListener { onItemClicked(item) }
        }
    }
}