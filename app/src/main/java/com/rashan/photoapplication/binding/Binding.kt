package com.rashan.photoapplication.binding

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.ui.overview.adapter.PhotoAdapter

@BindingAdapter("visibile")
fun bindViewVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String>) {
    text.value?.let {
        Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapter_photo_list")
fun bindAdapterPhotoList(view: RecyclerView, photoList: List<Photo>?) {
    (view.adapter as? PhotoAdapter)?.replacePhotoList(photoList)
}

@BindingAdapter("set_image_url")
fun ImageView.bindImageUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}
