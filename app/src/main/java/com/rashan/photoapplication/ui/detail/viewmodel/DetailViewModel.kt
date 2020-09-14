package com.rashan.photoapplication.ui.detail.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    lateinit var photo: Photo

    fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.updatePhotoFavouriteStatus(photoId, isFavourite)
        }
}