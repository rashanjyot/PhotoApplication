package com.rashan.photoapplication.ui.detail.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.rashan.photoapplication.R
import com.rashan.photoapplication.databinding.ActivityDetailBinding
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

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