package com.rashan.photoapplication.ui.overview.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rashan.photoapplication.models.Photo
import com.rashan.photoapplication.ui.overview.adapter.PhotoAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterPhotoList")
fun bindAdapterPhotoList(view: RecyclerView, photoList: List<Photo>?) {
    (view.adapter as? PhotoAdapter)?.replacePhotoList(photoList)
}
