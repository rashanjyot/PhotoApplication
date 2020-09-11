package com.rashan.photoapplication.persistence

import androidx.room.*
import com.rashan.photoapplication.model.domain.PhotoFavourite

@Dao
interface PhotoFavouriteDao {
    @Query("SELECT * FROM photofavourite")
    fun getAll(): List<PhotoFavourite>

    @Query("SELECT * FROM photofavourite WHERE id = :id")
    fun getById(id: String): List<PhotoFavourite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(photoFavourite: PhotoFavourite)

    @Delete
    fun delete(photoFavourite: PhotoFavourite)
}