package com.rashan.photoapplication.network

import com.rashan.photoapplication.model.domain.Photo
import retrofit2.http.GET

interface PhotoService {

    @GET("list")
    suspend fun fetchPhotoList(): List<Photo>
}