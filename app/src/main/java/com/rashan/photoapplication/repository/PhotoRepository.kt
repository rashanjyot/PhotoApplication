package com.rashan.photoapplication.repository

import androidx.lifecycle.LiveData
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.persistence.PhotoDao
import com.rashan.photoapplication.network.PhotoClient
import com.rashan.photoapplication.network.generic.Resource
import com.rashan.photoapplication.network.generic.ResponseHandler
import com.rashan.photoapplication.network.generic.Status
import java.lang.Exception
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoClient: PhotoClient,
    private val photoDao: PhotoDao,
    private val responseHandler: ResponseHandler
) {

    var photoListLiveData: LiveData<List<Photo>> = photoDao.getAllAsLiveData()

    suspend fun fetchPhotoList(onError: (String) -> Unit) {
        val photoList: List<Photo>? = photoDao.getAll()
        if (photoList == null || photoList.isEmpty()) {
            val photoListResource = fetchRemotePhotoList()
            when (photoListResource.status) {
                Status.SUCCESS -> photoDao.insertMultiple(photoListResource.data!!)
                Status.ERROR -> onError(photoListResource.message!!)
            }
        }
    }

    private suspend fun fetchRemotePhotoList(): Resource<List<Photo>> {
        return try {
            responseHandler.handleSuccess(photoClient.fetchPhotoList())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}