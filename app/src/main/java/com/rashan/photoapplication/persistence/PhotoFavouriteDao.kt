package com.rashan.photoapplication.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rashan.photoapplication.model.domain.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getAllAsLiveData(): LiveData<List<Photo>>

    @Query("SELECT * FROM photo")
    fun getAll(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMultiple(photoList: List<Photo>)
}