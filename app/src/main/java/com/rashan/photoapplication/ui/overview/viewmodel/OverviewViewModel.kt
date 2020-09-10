package com.rashan.photoapplication.ui.overview.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.rashan.photoapplication.models.Photo
import com.rashan.photoapplication.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import network.generic.Status

class OverviewViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    var photoListLiveData: MutableLiveData<List<Photo>> = MutableLiveData(mutableListOf())

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val isRetryAllowed: ObservableBoolean = ObservableBoolean(false)
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    fun fetchPhotoList() = viewModelScope.launch(Dispatchers.IO) {

        isRetryAllowed.set(false)

        isLoading.set(true)
        val photoListResource = photoRepository.fetchPhotoList()
        isLoading.set(false)

        when (photoListResource.status) {
            Status.SUCCESS -> onPhotoListFetchSuccess(photoListResource.data!!)
            Status.ERROR -> onPhotoListFetchError(photoListResource.message!!)
        }
    }

    private fun onPhotoListFetchSuccess(photoList: List<Photo>) {
        photoListLiveData.postValue(photoList)
    }

    private fun onPhotoListFetchError(message: String) {
        toastLiveData.postValue(message)
        isRetryAllowed.set(true)
    }

}