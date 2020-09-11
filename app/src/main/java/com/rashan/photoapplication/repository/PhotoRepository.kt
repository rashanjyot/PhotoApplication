package com.rashan.photoapplication.repository

import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.model.networkDto.mapToDomain
import com.rashan.photoapplication.persistence.PhotoFavouriteDao
import com.rashan.photoapplication.network.PhotoClient
import com.rashan.photoapplication.network.generic.Resource
import com.rashan.photoapplication.network.generic.ResponseHandler
import java.lang.Exception
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoClient: PhotoClient,
    private val photoFavouriteDao: PhotoFavouriteDao,
    private val responseHandler: ResponseHandler
) {
    suspend fun fetchPhotoList(): Resource<List<Photo>> {
        return try {
            responseHandler.handleSuccess(photoClient.fetchPhotoList().map {
                val isFavourite = photoFavouriteDao.getById(it.id).isNotEmpty()
                it.mapToDomain().apply { this.isFavourite = isFavourite }
            })
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}