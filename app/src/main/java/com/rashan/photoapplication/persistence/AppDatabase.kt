package com.rashan.photoapplication.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rashan.photoapplication.model.domain.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}