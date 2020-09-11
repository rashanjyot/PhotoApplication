package com.rashan.photoapplication.model.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    var isFavourite: Boolean = false
) : Parcelable

@Entity
data class PhotoFavourite(
    @PrimaryKey val id: String
)