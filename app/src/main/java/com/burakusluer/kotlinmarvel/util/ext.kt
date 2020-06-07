package com.burakusluer.kotlinmarvel.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun ImageView.downloadImage(url: String?) {
    Glide.with(this.context).load(url).placeholder(CircularProgressDrawable(this.context).apply {
        strokeWidth = 8F
        centerRadius = 40F
    }).into(this)
}

@BindingAdapter(value = ["android:loadImageFromUrl"])
fun loadUrl(imageView: ImageView, url: String?) {
    imageView.downloadImage(url)
}