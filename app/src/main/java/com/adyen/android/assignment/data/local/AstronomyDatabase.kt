package com.adyen.android.assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adyen.android.assignment.data.local.dao.AstronomyPictureDao
import com.adyen.android.assignment.data.local.models.AstronomyPictureEntity

@Database(entities = [AstronomyPictureEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AstronomyDatabase : RoomDatabase() {

    abstract fun astronomyPictureDao(): AstronomyPictureDao
}