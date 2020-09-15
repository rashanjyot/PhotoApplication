package com.rashan.photoapplication.ui.overview.viewmodel

import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.rashan.photoapplication.model.domain.Photo
import com.rashan.photoapplication.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OverviewViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val allPhotosLiveData = photoRepository.allPhotosLiveData
    private val favouritePhotosLiveData = photoRepository.favouritePhotosLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false) // determines progressBar's visibility
    val isRetryAllowed: ObservableBoolean = ObservableBoolean(false) // determines retryButton's visibility
    val showOnlyFavourites: ObservableBoolean = ObservableBoolean(false) // determines favourite filter's state
    val toastLiveData: MutableLiveData<String> = MutableLiveData() // displaying toast messages

    val photoListLiveData: MediatorLiveData<List<Photo>> = MediatorLiveData()

    init {
        photoListLiveData.addSource(photoRepository.allPhotosLiveData, Observer {
            // update photoListLiveData only if all photos are to be shown
            if (!showOnlyFavourites.get()) {
                photoListLiveData.postValue(it)
            }
        })

        photoListLiveData.addSource(photoRepository.favouritePhotosLiveData, Observer {
            // update photoListLiveData only if favourite photos are to be shown
            if (showOnlyFavourites.get()) {
                photoListLiveData.postValue(it)
            }
        })
    }

    init {
        // Observe showOnlyFavourites to filter for favourite photos
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

    /**
     * Responsible for refreshing LiveData objects ([allPhotosLiveData] & [favouritePhotosLiveData])
     * if needed based on responses from repository's data sources
     */
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