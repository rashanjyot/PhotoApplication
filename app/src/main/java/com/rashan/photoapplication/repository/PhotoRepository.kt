package com.rashan.photoapplication.repository

import androidx.lifecycle.LiveData
import com.rashan.photoapplication.model.domain.Photo

interface PhotoRepository {

    var allPhotosLiveData: LiveData<List<Photo>>
    var favouritePhotosLiveData: LiveData<List<Photo>>

    suspend fun updatePhotoList(onError: (String) -> Unit)

    suspend fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean)

    fun getRoomLiveDataForPhotoId(photoId: String): LiveData<Photo>

}