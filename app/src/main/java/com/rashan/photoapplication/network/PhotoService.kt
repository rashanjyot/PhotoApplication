package com.rashan.photoapplication.network

import com.rashan.photoapplication.model.networkDto.PhotoNetworkDto
import retrofit2.http.GET

interface PhotoService {

    @GET("list")
    suspend fun fetchPhotoList(): List<PhotoNetworkDto>
}