package com.rashan.photoapplication.ui.overview.adapter

import android.app.Activity
import android.view.*
import androidx.core.view.GestureDetectorCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rashan.photoapplication.R
import com.rashan.photoapplication.databinding.ItemPhotoOverviewBinding
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.ui.detail.activity.DetailActivity
import com.rashan.photoapplication.ui.overview.activity.OverviewActivity

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

            val photoDoubleTapDetector = GestureDetectorCompat(
                photoImageview.context,
                PhotoDoubleTapListener(root, photo)
            )

            photoImageview.setOnTouchListener { view, motionEvent ->
                photoDoubleTapDetector.onTouchEvent(motionEvent)
                true
            }
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

    class PhotoDoubleTapListener constructor(
        private val view: View,
        private val photo: Photo
    ) :
        GestureDetector.SimpleOnGestureListener() {

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            (view.context as OverviewActivity).updatePhotoFavouriteStatus(
                photo.id,
                !photo.isFavourite
            )
            return super.onDoubleTap(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            openDetailActivityForPhoto(view, photo)
            return super.onSingleTapConfirmed(e)
        }
    }

}

private fun openDetailActivityForPhoto(view: View, photo: Photo) {
    val context = view.context
    if (context is Activity) {
        DetailActivity.startActivity(context, photo)
    }
}