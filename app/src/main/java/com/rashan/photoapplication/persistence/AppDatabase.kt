package com.rashan.photoapplication.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rashan.photoapplication.model.domain.PhotoFavourite

@Database(entities = [PhotoFavourite::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoFavouriteDao(): PhotoFavouriteDao
}