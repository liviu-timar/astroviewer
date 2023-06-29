package com.liviutimar.astroviewer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.liviutimar.astroviewer.data.db.dao.PictureDao
import com.liviutimar.astroviewer.data.db.entities.PictureEntity

@Database(entities = [PictureEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PictureDatabase : RoomDatabase() {

    abstract fun pictureDao(): PictureDao
}