package com.rashan.photoapplication.di

import com.rashan.photoapplication.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import network.PhotoClient
import network.generic.ResponseHandler

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun providePhotoRepository(photoClient: PhotoClient, responseHandler: ResponseHandler): PhotoRepository {
        return PhotoRepository(photoClient, responseHandler)
    }

}