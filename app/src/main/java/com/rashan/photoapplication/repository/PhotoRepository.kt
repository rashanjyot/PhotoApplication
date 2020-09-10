package com.rashan.photoapplication.repository

import com.rashan.photoapplication.models.Photo
import network.PhotoClient
import network.generic.Resource
import network.generic.ResponseHandler
import java.lang.Exception
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoClient: PhotoClient,
    private val responseHandler: ResponseHandler
) {
    suspend fun fetchPhotoList(): Resource<List<Photo>> {
        return try {
            responseHandler.handleSuccess(photoClient.fetchPhotoList())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}