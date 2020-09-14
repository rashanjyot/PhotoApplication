package com.rashan.photoapplication.ui.detail.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.repository.PhotoRepository
import com.rashan.photoapplication.ui.detail.activity.DetailActivity.Companion.EXTRA_PHOTO_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val photoId = savedStateHandle.get<String>(EXTRA_PHOTO_ID)!!
    lateinit var photoLiveData: LiveData<Photo>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            photoLiveData = photoRepository.getRoomLiveDataForPhotoId(photoId)
        }
    }

    fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.updatePhotoFavouriteStatus(photoId, isFavourite)
        }
}