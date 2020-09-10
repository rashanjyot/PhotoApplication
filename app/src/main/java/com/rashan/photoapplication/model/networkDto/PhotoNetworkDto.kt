package com.rashan.photoapplication.model.networkDto

import com.google.gson.annotations.SerializedName
import com.rashan.photoapplication.model.domain.Photo

data class PhotoNetworkDto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)

fun PhotoNetworkDto.mapToDomain(): Photo {
   return Photo(id, author, width, height, url, downloadUrl)
}