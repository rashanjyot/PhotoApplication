package com.rashan.photoapplication.ui.overview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rashan.photoapplication.databinding.ActivityOverviewBinding
import com.rashan.photoapplication.models.Photo
import dagger.hilt.android.AndroidEntryPoint
import network.generic.Resource
import network.generic.Status.*

@AndroidEntryPoint
class OverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverviewBinding
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.photoListLiveData.observe(this, Observer<Resource<List<Photo>>> {
            when (it.status) {
                SUCCESS -> showPhotoList(it.data!!)
                ERROR -> showErrorAndRetryButton(it.message!!)
                LOADING -> showProgressBar()
            }
        })
    }

    private fun showPhotoList(photoList: List<Photo>) {
        hideProgressBar()
        binding.photoRecyclerView.adapter = PhotoAdapter(photoList)
    }

    private fun showErrorAndRetryButton(message: String) {
        hideProgressBar()
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        binding.retryButton.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }
}