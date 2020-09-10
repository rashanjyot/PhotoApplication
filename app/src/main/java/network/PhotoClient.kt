package network

import javax.inject.Inject

class PhotoClient @Inject constructor(
    private val photoService: PhotoService
) {
    suspend fun fetchPhotoList() = photoService.fetchPhotoList()
}