package com.rashan.photoapplication.ui.overview.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.rashan.photoapplication.R
import com.rashan.photoapplication.databinding.ActivityOverviewBinding
import com.rashan.photoapplication.ui.overview.viewmodel.OverviewViewModel
import com.rashan.photoapplication.ui.overview.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverviewBinding
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_overview)

        binding.apply {
            lifecycleOwner = this@OverviewActivity
            adapter = PhotoAdapter()
            vm = viewModel.apply { refreshPhotoList() }
        }
    }

    fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean) {
        viewModel.updatePhotoFavouriteStatus(photoId, isFavourite)
    }

}