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

class PhotoRepositoryImpl @Inject constructor(
    private val photoClient: PhotoClient,
    private val photoDao: PhotoDao,
    private val responseHandler: ResponseHandler
) : PhotoRepository {

    override val allPhotosLiveData: LiveData<List<Photo>> = photoDao.getAllAsLiveData()
    override val favouritePhotosLiveData: LiveData<List<Photo>> = photoDao.getFavouritesAsLiveData()

    /**
     * Checks if photos are present in the local DB storage, else fetches them from
     * remote data source and stores in local DB, which acts like the single source of truth.
     * In case, the request to remote data source fails, invokes [onError] param.
     */
    override suspend fun updatePhotoListIfRequired(onError: (String) -> Unit) {
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

    override suspend fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean) {
        photoDao.updatePhotoFavouriteStatus(photoId, isFavourite)
    }

    override fun getPhotoAsLiveData(photoId: String): LiveData<Photo> {
        return photoDao.getPhotoAsLiveData(photoId)
    }

}