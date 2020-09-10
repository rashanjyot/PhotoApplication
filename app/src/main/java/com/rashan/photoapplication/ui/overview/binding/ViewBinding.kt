package com.rashan.photoapplication.ui.overview.binding

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("loading")
fun bindViewVisibility(view: ProgressBar, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String>) {
    text.value?.let {
        Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("retry_allowed")
fun bindRetryButtonAvailable(view: Button, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
