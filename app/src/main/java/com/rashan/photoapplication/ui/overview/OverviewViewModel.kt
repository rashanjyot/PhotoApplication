package com.rashan.photoapplication.ui.overview

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.rashan.photoapplication.models.Photo
import com.rashan.photoapplication.repository.PhotoRepository
import network.generic.Resource

class OverviewViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    val photoListLiveData: LiveData<Resource<List<Photo>>> = liveData {
        emit(Resource.loading(null))
        emit(photoRepository.fetchPhotoList())
    }

}