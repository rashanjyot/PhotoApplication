package com.rashan.photoapplication.ui.overview.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.rashan.photoapplication.models.Photo
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
