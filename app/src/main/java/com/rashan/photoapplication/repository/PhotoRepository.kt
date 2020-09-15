package com.rashan.photoapplication.repository

import androidx.lifecycle.LiveData
import com.rashan.photoapplication.model.domain.Photo

interface PhotoRepository {

    val allPhotosLiveData: LiveData<List<Photo>>
    val favouritePhotosLiveData: LiveData<List<Photo>>

    suspend fun updatePhotoListIfRequired(onError: (String) -> Unit)

    suspend fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean)

    fun getPhotoAsLiveData(photoId: String): LiveData<Photo>

}