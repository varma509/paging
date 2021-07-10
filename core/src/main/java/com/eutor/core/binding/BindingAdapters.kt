package com.eutor.core.binding

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton


@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (!isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("setBgByType")
fun bindViewBgColor(view: View, type: Int) {
    val color = when (type) {
        0 -> Color.parseColor("#FFFFFF")
        1 -> Color.parseColor("#0E6FF7")
        2 -> Color.parseColor("#62C370")
        3 -> Color.parseColor("#C15D5D")
        else -> Color.parseColor("#FFFFFF")
    }
    view.setBackgroundColor(color)

}

@BindingAdapter("isEmptyGone")
fun isEmptyGone(view: TextView, data: String?) {
    view.visibility = if (data == null || data.trim().isEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(imageUrl)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}

@BindingAdapter("src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}


@BindingAdapter("subTitle")
fun setSubTitle(tv: TextView, data: String) {
    if (!data.trim().isEmpty()) {
        tv.text = data
        tv.visibility = View.VISIBLE
    } else {
        tv.visibility = View.GONE
    }
}

/*@BindingAdapter("adapter")
fun setAdapter(
    rv: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {
    rv.adapter = adapter
}*/

@BindingAdapter("horizontalAdapter")
fun horizontalAdapter(
    rv: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {
    rv.layoutManager = LinearLayoutManager(rv.context, LinearLayoutManager.HORIZONTAL, false)
    rv.adapter = adapter
}


@BindingAdapter("spannable")
fun setsSpannable(tv: TextView, strname: String) {
    Log.e("MainActivity", "custom function called")
    val nameCaps = strname
    tv.text = nameCaps

}

@BindingAdapter("spanTitle", "spanText","spanSubText")
fun namespan(tv: TextView, spanTitle: String, spanText: String,spanSubText:String) {
   // Log.e(spanText, "$spanTitle")

    var sb = SpannableStringBuilder(spanTitle)
    val bss = StyleSpan(Typeface.BOLD)
    sb.setSpan(bss, 0, spanTitle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    if(!spanText.equals("null")||spanText!=null) {
        if (spanTitle.equals("Rs."))
            sb.append(": ").append(spanSubText).append(" ( ").append(spanText).append(" )")
        else if (spanSubText.trim().isEmpty()) {
            sb.append(" ").append(spanText)
        } else
            sb.append(": ").append(spanSubText).append(" | ").append(spanText)
        tv.setText(sb)
    }

}

@BindingAdapter("spanTitle", "spanText")
fun namespan(tv: TextView, spanTitle: String, spanText: String) {

          if(spanText.isNotEmpty()) {
              val str = SpannableStringBuilder("$spanTitle: $spanText")
              str.setSpan(
                  StyleSpan(Typeface.BOLD),
                  0,
                  spanTitle.length,
                  Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
              )
              tv.text = str
          }else{
              tv.visibility=View.GONE
          }

}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).into(this)
}



