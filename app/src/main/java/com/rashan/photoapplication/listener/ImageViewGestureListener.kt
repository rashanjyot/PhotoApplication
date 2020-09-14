package com.rashan.photoapplication.listener

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.core.view.GestureDetectorCompat
import com.rashan.photoapplication.base.BaseActivity
import com.rashan.photoapplication.model.domain.Photo

class ImageViewGestureListener constructor(
    private val view: View,
    private val photo: Photo,
    private val onSingleTapConfirmed: (() -> Unit)? = null
) :
    GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        (view.context as BaseActivity).updatePhotoFavouriteStatus(
            photo.id,
            !photo.isFavourite
        )
        return super.onDoubleTap(e)
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        onSingleTapConfirmed?.invoke()
        return super.onSingleTapConfirmed(e)
    }
}

fun ImageView.setupImageViewGestureDetector(
    photo: Photo,
    onSingleTapConfirmed: (() -> Unit)? = null
) {
    val imageViewGestureDetector = GestureDetectorCompat(
        this.context,
        ImageViewGestureListener(
            this, photo, onSingleTapConfirmed = onSingleTapConfirmed
        )
    )

    this.setOnTouchListener { view, motionEvent ->
        imageViewGestureDetector.onTouchEvent(motionEvent)
        true
    }
}