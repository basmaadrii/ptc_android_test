package android.ptc.com.ptcflixing.fragments

import android.content.Context
import android.os.Bundle
import android.ptc.com.ptcflixing.MainActivity
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.models.Configurations
import android.ptc.com.ptcflixing.viewModels.ProductListViewModel
import android.view.*
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*


class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailFragment()
    }

    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.product_detail_fragment, container, false)
        setHasOptionsMenu(true)
        val sharedPref = (activity as MainActivity).getSharedPreferences((activity as MainActivity).packageName, Context.MODE_PRIVATE)
        val configurations = sharedPref.getString((activity as MainActivity).packageName + "_configurations", "")?.let { Configurations.fromJson(it) }

        val format = NumberFormat.getCurrencyInstance(Locale("en-NG"))
        format.currency = Currency.getInstance(configurations?.metadata?.currency?.iso)

        viewModel = ViewModelProvider((activity as MainActivity), ViewModelProvider.NewInstanceFactory()).get(ProductListViewModel::class.java)

        viewModel.getProduct().observe(viewLifecycleOwner, Observer { product ->
            (activity as MainActivity).supportActionBar?.title = product.brand
            view.findViewById<TextView>(R.id.text_product_name).text = product.name
            view.findViewById<TextView>(R.id.text_product_price).text = format.format(product.price).toString()
            view.findViewById<TextView>(R.id.text_product_discount).text = "- " + product.maxSavingPercentage.toString() + "%"
            view.findViewById<TextView>(R.id.text_product_special_price).text = format.format(product.specialPrice).toString()
            view.findViewById<TextView>(R.id.text_summary).text = product.summary.shortDescription
            view.findViewById<TextView>(R.id.text_seller_name).text = product.sellerEntity.name
            view.findViewById<TextView>(R.id.text_seller_delivery).text = product.sellerEntity.deliveryTime
            view.findViewById<RatingBar>(R.id.rating).rating = product.rating.average.toFloat()
            view.findViewById<TextView>(R.id.text_rating_count).text = product.rating.ratingsTotal.toString() + " ratings"
            val image = view.findViewById<ImageView>(R.id.image_product)
            Glide.with(this).load(product.imageList[0]).fitCenter().into(image)
        })
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.search).isVisible = false
    }
}