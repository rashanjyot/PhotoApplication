package com.rashan.photoapplication.ui.overview.viewmodel

import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rashan.photoapplication.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OverviewViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    var photoListLiveData = photoRepository.photoListLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val isRetryAllowed: ObservableBoolean = ObservableBoolean(false)
    val showOnlyFavourites: ObservableBoolean = ObservableBoolean(false)
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        showOnlyFavourites.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                refreshPhotoList()
            }
        })
    }
    fun refreshPhotoList() = viewModelScope.launch(Dispatchers.IO) {

        isRetryAllowed.set(false)

        isLoading.set(true)
        photoRepository.fetchPhotoList(
            showOnlyFavourites.get(),
            onError = {
                isRetryAllowed.set(true)
                toastLiveData.postValue(it)
            }
        )
        isLoading.set(false)

    }
}