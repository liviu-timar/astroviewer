package com.liviutimar.astroviewer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.liviutimar.astroviewer.data.db.dao.AstronomyPictureDao
import com.liviutimar.astroviewer.data.db.models.AstronomyPictureEntity

@Database(entities = [AstronomyPictureEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AstronomyDatabase : RoomDatabase() {

    abstract fun astronomyPictureDao(): AstronomyPictureDao
}