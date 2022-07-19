package com.adyen.android.assignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adyen.android.assignment.data.db.dao.AstronomyPictureDao
import com.adyen.android.assignment.data.db.models.AstronomyPictureEntity

@Database(entities = [AstronomyPictureEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AstronomyDatabase : RoomDatabase() {

    abstract fun astronomyPictureDao(): AstronomyPictureDao
}