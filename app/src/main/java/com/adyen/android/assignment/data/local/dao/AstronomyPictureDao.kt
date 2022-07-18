package com.adyen.android.assignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adyen.android.assignment.data.local.models.AstronomyPictureEntity

@Dao
interface AstronomyPictureDao {

    @Query("SELECT * FROM Pictures LIMIT :count")
    suspend fun get(count: Int): List<AstronomyPictureEntity>

    @Query("SELECT * FROM Pictures WHERE id=:id")
    suspend fun getById(id: Int): AstronomyPictureEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictures: List<AstronomyPictureEntity>)

    @Query("DELETE FROM Pictures")
    suspend fun deleteAll()
}