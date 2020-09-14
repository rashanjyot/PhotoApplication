package com.rashan.photoapplication.listener

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.core.view.GestureDetectorCompat
import com.rashan.photoapplication.base.BaseActivity
import com.rashan.photoapplication.model.domain.Photo

class ImageViewGestureListener constructor(
    private val onSingleTapConfirmed: (() -> Unit)? = null,
    private val onDoubleTap: () -> Unit
) :
    GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        onDoubleTap.invoke()
        return super.onDoubleTap(e)
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        onSingleTapConfirmed?.invoke()
        return super.onSingleTapConfirmed(e)
    }
}

fun ImageView.setupImageViewGestureDetector(
    onSingleTapConfirmed: (() -> Unit)? = null,
    onDoubleTap: (() -> Unit)
) {
    val imageViewGestureDetector = GestureDetectorCompat(
        this.context,
        ImageViewGestureListener(
            onSingleTapConfirmed = onSingleTapConfirmed,
            onDoubleTap = onDoubleTap
        )
    )

    this.setOnTouchListener { view, motionEvent ->
        imageViewGestureDetector.onTouchEvent(motionEvent)
        true
    }
}