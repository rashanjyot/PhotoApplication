package com.rashan.photoapplication.ui.overview.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.rashan.photoapplication.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    var photoListLiveData = photoRepository.photoListLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val isRetryAllowed: ObservableBoolean = ObservableBoolean(false)
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    fun fetchPhotoList() = viewModelScope.launch(Dispatchers.IO) {

        isRetryAllowed.set(false)

        isLoading.set(true)
        photoRepository.fetchPhotoList(
            onError = {
                isRetryAllowed.set(true)
                toastLiveData.postValue(it)
            }
        )
        isLoading.set(false)

    }
}