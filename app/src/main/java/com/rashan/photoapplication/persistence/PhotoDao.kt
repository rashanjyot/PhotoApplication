package com.rashan.photoapplication.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rashan.photoapplication.model.domain.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo where isFavourite = 1")
    fun getFavouritesAsLiveData(): LiveData<List<Photo>>

    @Query("SELECT * FROM photo")
    fun getAllAsLiveData(): LiveData<List<Photo>>

    @Query("SELECT * FROM photo")
    fun getAll(): List<Photo>

    @Query("SELECT * FROM photo where id = :photoId")
    fun getPhotoAsLiveData(photoId: String): LiveData<Photo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMultiple(photoList: List<Photo>)

    @Query("Update photo SET isFavourite = :isFavourite WHERE id = :photoId")
    suspend fun updatePhotoFavouriteStatus(photoId: String, isFavourite: Boolean)
}