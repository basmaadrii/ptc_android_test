package android.ptc.com.ptcflixing.ui.utils

import android.content.Context
import android.ptc.com.ptcflixing.R
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object ViewUtils {
    const val STATUS_ERROR = "Something Went Wrong"

    fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

    fun Context.glidePlaceHolder(): CircularProgressDrawable {
        return CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            setColorSchemeColors(getColorCompat(R.color.jumia_color))
            start()
        }
    }


    fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) =
        this?.let { Toast.makeText(it, textId, duration).show() }


    fun span(priceTextView: TextView, before: String?, currency: String?) {
        if (!before.isNullOrBlank()) {
            val ssb = SpannableStringBuilder(currency)
            ssb.setSpan(
                null,
                0,
                ssb.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            val strikeThroughSpan = StrikethroughSpan()

            ssb.append("")
            ssb.append(before)
            ssb.setSpan(
                strikeThroughSpan,
                ssb.length - before.length,
                ssb.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            priceTextView.setText(ssb, TextView.BufferType.EDITABLE)
        }
    }



}