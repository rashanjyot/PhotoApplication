package com.rashan.photoapplication.ui.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rashan.photoapplication.R
import com.rashan.photoapplication.databinding.ItemPhotoOverviewBinding
import com.rashan.photoapplication.models.Photo

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
        holder.binding.photo = photoList[position]
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