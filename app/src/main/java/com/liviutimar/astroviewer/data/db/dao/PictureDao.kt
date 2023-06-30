package com.liviutimar.astroviewer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.liviutimar.astroviewer.data.db.entities.PictureEntity

@Dao
interface PictureDao {

    @Query("SELECT * FROM Pictures LIMIT :count")
    suspend fun get(count: Int): List<PictureEntity>

    @Query("SELECT * FROM Pictures WHERE id=:id")
    suspend fun getById(id: Int): PictureEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictures: List<PictureEntity>)

    @Query("DELETE FROM Pictures")
    suspend fun deleteAll()
}