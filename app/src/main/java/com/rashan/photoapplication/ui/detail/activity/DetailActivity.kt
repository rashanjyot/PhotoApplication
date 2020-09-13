package com.rashan.photoapplication.ui.detail.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.rashan.photoapplication.R
import com.rashan.photoapplication.base.BaseActivity
import com.rashan.photoapplication.databinding.ActivityDetailBinding
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@DetailActivity
        }
    }

    companion object {

        private const val EXTRA_PHOTO = "EXTRA_PHOTO"

        fun startActivity(activity: Activity, photo: Photo) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_PHOTO, photo)
            activity.startActivity(intent)
        }
    }
}