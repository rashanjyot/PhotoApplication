package com.rashan.photoapplication.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rashan.photoapplication.databinding.ItemPhotoOverviewBinding
import com.rashan.photoapplication.models.Photo
import utility.setImageFromUrl

class PhotoAdapter constructor(var photoList: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    //    private val photoList: MutableList<Photo> = mutableListOf(
//        Photo("0", "Alejandro Escamilla", 5616, 3744, "https://unsplash.com/photos/yC-Yzbqy7PY", "https://picsum.photos/id/0/5616/3744"),
//        Photo("0", "Alejandro Escamilla", 5616, 3744, "https://unsplash.com/photos/yC-Yzbqy7PY", "https://picsum.photos/id/0/5616/3744"),
//        Photo("0", "Alejandro Escamilla", 5616, 3744, "https://unsplash.com/photos/yC-Yzbqy7PY", "https://picsum.photos/id/0/5616/3744"),
//        Photo("0", "Alejandro Escamilla", 5616, 3744, "https://unsplash.com/photos/yC-Yzbqy7PY", "https://picsum.photos/id/0/5616/3744")
//    )

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

    class PhotoViewHolder(private val binding: ItemPhotoOverviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.apply {
                photographerNameTextview.text = photo.author
                photoImageview.setImageFromUrl(photo.downloadUrl)
            }
        }
    }
}