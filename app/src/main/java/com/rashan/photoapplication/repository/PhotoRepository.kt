package com.rashan.photoapplication.repository

import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.model.networkDto.mapToDomain
import kotlinx.coroutines.flow.asFlow
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
            responseHandler.handleSuccess(photoClient.fetchPhotoList().map { it.mapToDomain() })
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}