package com.rashan.photoapplication.model.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Photo(
    @PrimaryKey
    var id: String,

    var author: String,

    var width: Int,

    var height: Int,

    var url: String,

    @SerializedName("download_url")
    var downloadUrl: String,

    var isFavourite: Boolean = false
) : Parcelable