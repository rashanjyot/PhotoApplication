package com.rashan.photoapplication.ui.detail.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.rashan.photoapplication.R
import com.rashan.photoapplication.base.BaseActivity
import com.rashan.photoapplication.base.onPhotoDoubleTap
import com.rashan.photoapplication.databinding.ActivityDetailBinding
import com.rashan.photoapplication.listener.setupImageViewGestureDetector
import com.rashan.photoapplication.ui.detail.viewmodel.DetailViewModel
import com.rashan.photoapplication.utility.DownloadUtility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@DetailActivity
            vm = viewModel

            openUnsplashUrlButton.setOnClickListener { openUnsplashUrlInBrowser() }
            downloadPhotoButton.setOnClickListener { downloadPhoto() }

            photoImageview.setupImageViewGestureDetector(
                onDoubleTap = { (photoImageview.context as BaseActivity).onPhotoDoubleTap(viewModel.photoLiveData.value!!) }
            )
        }
    }

    override fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean) {
        viewModel.updatePhotoFavouriteStatus(photoId, isFavourite)
    }

    private fun openUnsplashUrlInBrowser() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(viewModel.photoLiveData.value!!.url)
        startActivity(intent)
    }

    private fun downloadPhoto() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                EXTERNAL_STORAGE_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            DownloadUtility.downloadImage(
                applicationContext,
                viewModel.photoLiveData.value!!.downloadUrl
            )
        } else {
            initiatePermissionRequest()
        }
    }

    private fun initiatePermissionRequest() {
        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                EXTERNAL_STORAGE_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED -> {
                downloadPhoto()
            }

            shouldShowRequestPermissionRationale(EXTERNAL_STORAGE_PERMISSION) -> {
                Toast.makeText(
                    applicationContext,
                    "Please provide storage permission",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
                requestPermissions(arrayOf(EXTERNAL_STORAGE_PERMISSION), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    downloadPhoto()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please provide storage permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    companion object {

        private const val EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
        private const val EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1234
        const val EXTRA_PHOTO_ID = "EXTRA_PHOTO_ID"

        fun startActivity(activity: Activity, photoId: String) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_PHOTO_ID, photoId)
            activity.startActivity(intent)
        }
    }
}