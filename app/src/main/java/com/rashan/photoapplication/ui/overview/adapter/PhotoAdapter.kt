package com.rashan.photoapplication.ui.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rashan.photoapplication.databinding.ItemPhotoOverviewBinding
import com.rashan.photoapplication.models.Photo
import utility.setImageFromUrl

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val photoList: MutableList<Photo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoOverviewBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount() = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photoList[position]
        holder.bind(photo)
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

    class PhotoViewHolder(private val binding: ItemPhotoOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.apply {
                photographerNameTextview.text = photo.author
                photoImageview.setImageFromUrl(photo.downloadUrl)
            }
        }
    }
}