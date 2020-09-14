package com.rashan.photoapplication.ui.overview.viewmodel

import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OverviewViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val allPhotosLiveData = photoRepository.allPhotosLiveData
    private val favouritePhotosLiveData = photoRepository.favouritePhotosLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val isRetryAllowed: ObservableBoolean = ObservableBoolean(false)
    val showOnlyFavourites: ObservableBoolean = ObservableBoolean(false)
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    val photoListLiveData: MediatorLiveData<List<Photo>> = MediatorLiveData()

    init {
        photoListLiveData.addSource(photoRepository.allPhotosLiveData, Observer {
            if (!showOnlyFavourites.get()) {
                photoListLiveData.postValue(it)
            }
        })

        photoListLiveData.addSource(photoRepository.favouritePhotosLiveData, Observer {
            if (showOnlyFavourites.get()) {
                photoListLiveData.postValue(it)
            }
        })
    }

    init {
        showOnlyFavourites.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (showOnlyFavourites.get()) {
                    photoListLiveData.postValue(favouritePhotosLiveData.value)
                } else {
                    photoListLiveData.postValue(allPhotosLiveData.value)
                }
            }
        })
    }

    fun refreshPhotoList() = viewModelScope.launch(Dispatchers.IO) {

        isRetryAllowed.set(false)

        isLoading.set(true)
        photoRepository.updatePhotoListIfRequired(
            onError = {
                isRetryAllowed.set(true)
                toastLiveData.postValue(it)
            }
        )
        isLoading.set(false)

    }

    fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.updatePhotoFavouriteStatus(photoId, isFavourite)
        }
}