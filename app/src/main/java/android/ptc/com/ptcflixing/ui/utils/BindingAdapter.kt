package android.ptc.com.ptcflixing.ui.utils

import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.ShimmerFrameLayout
import android.ptc.com.ptcflixing.ui.utils.ViewUtils.glidePlaceHolder

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setNetworkImage")
    fun setNetworkImage(view: ImageView, image: String?) {
        if (image == null) return
        val options = RequestOptions()
            .override(650, 375)
            .placeholder(view.context.glidePlaceHolder())
        //.error(R.drawable.)

        Glide.with(view.context)
            .load(image)
            .apply(options)
            .into(view)
    }



    @JvmStatic
    @BindingAdapter("setCircleNetworkImage")
    fun setCircleNetworkImage(view: ImageView, image: String?) {
        if (image == null) return
        Glide.with(view.context).load(image).apply(RequestOptions.circleCropTransform()).into(view)
    }


    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, image: Int?) {
        if (image == null) return
        Glide.with(view.context).load(image).apply(RequestOptions.circleCropTransform()).into(view)
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("invisibleGone")
    fun invisibleGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }


    @JvmStatic
    @BindingAdapter("startShimmer")
    fun startShimmer(view: ShimmerFrameLayout, start: Boolean) {
        if (start) view.startShimmer() else view.stopShimmer()
    }

    @JvmStatic
    @BindingAdapter("htmlText")
    fun htmlText(view: TextView, text: String?) {
        if (text == null)
            return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            view.text = Html.fromHtml(text)
        }
    }

    fun htmlDescription(text: String?): CharSequence {
        if (text == null)
            return ""
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(text)
        }
    }


    @JvmStatic
    @BindingAdapter("underLine")
    fun underLine(view: TextView, b: Boolean) {
        if (b)
            view.paintFlags = view.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    @JvmStatic
    @BindingAdapter("middleLine")
    fun middleLine(view: TextView, b: Boolean) {
        if (b)
            view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }


}