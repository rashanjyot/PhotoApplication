package com.rashan.photoapplication.ui.overview.adapter

import android.app.Activity
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rashan.photoapplication.R
import com.rashan.photoapplication.base.BaseActivity
import com.rashan.photoapplication.base.onPhotoDoubleTap
import com.rashan.photoapplication.databinding.ItemPhotoOverviewBinding
import com.rashan.photoapplication.listener.setupImageViewGestureDetector
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.ui.detail.activity.DetailActivity

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val photoList: MutableList<Photo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPhotoOverviewBinding>(
            inflater,
            R.layout.item_photo_overview,
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun getItemCount() = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.binding.apply {

            val photo = photoList[position]

            this.photo = photo
            adapter = this@PhotoAdapter

            photoImageview.setupImageViewGestureDetector(
                onDoubleTap = { (photoImageview.context as BaseActivity).onPhotoDoubleTap(photo) },
                onSingleTapConfirmed = { openDetailActivityForPhoto(root, photo.id) }
            )
        }
    }

    fun replacePhotoList(photoList: List<Photo>?) {
        if (photoList != null) {
            this.photoList.apply {
                clear()
                addAll(photoList)
            }
            notifyDataSetChanged()
        }
    }

    class PhotoViewHolder(val binding: ItemPhotoOverviewBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private fun openDetailActivityForPhoto(view: View, photoId: String) {
    val context = view.context
    if (context is Activity) {
        DetailActivity.startActivity(context, photoId)
    }
}