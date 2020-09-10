package utility

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageFromUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}