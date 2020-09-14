package com.rashan.photoapplication.ui.overview.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.rashan.photoapplication.R
import com.rashan.photoapplication.base.BaseActivity
import com.rashan.photoapplication.databinding.ActivityOverviewBinding
import com.rashan.photoapplication.ui.overview.viewmodel.OverviewViewModel
import com.rashan.photoapplication.ui.overview.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewActivity : BaseActivity() {

    private val binding: ActivityOverviewBinding by binding(R.layout.activity_overview)
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@OverviewActivity
            adapter = PhotoAdapter()
            vm = viewModel.apply { refreshPhotoList() }
        }
    }

    override fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean) {
        viewModel.updatePhotoFavouriteStatus(photoId, isFavourite)
    }

}