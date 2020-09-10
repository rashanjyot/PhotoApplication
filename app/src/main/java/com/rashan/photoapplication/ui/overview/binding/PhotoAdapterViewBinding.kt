package com.rashan.photoapplication.ui.overview.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("set_image_url")
fun ImageView.bindImageUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}