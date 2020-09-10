package network

import com.rashan.photoapplication.models.Photo
import retrofit2.Call
import retrofit2.http.GET

interface PhotoService {

    @GET("list")
    suspend fun fetchPhotoList(): List<Photo>
}