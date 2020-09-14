package com.rashan.photoapplication.di

import com.rashan.photoapplication.persistence.PhotoDao
import com.rashan.photoapplication.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import com.rashan.photoapplication.network.PhotoClient
import com.rashan.photoapplication.network.generic.ResponseHandler
import com.rashan.photoapplication.repository.PhotoRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun providePhotoRepository(
        photoClient: PhotoClient,
        photoDao: PhotoDao,
        responseHandler: ResponseHandler
    ): PhotoRepository {
        return PhotoRepositoryImpl(photoClient, photoDao, responseHandler)
    }

}