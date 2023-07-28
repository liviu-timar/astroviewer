package com.liviutimar.astroviewer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.liviutimar.astroviewer.data.db.entities.PictureEntity

@Dao
interface PictureDao {

    @Query("SELECT * FROM Pictures WHERE isFavorite = :isFavorite")
    suspend fun get(isFavorite: Boolean): List<PictureEntity>

    @Query("SELECT * FROM Pictures WHERE id = :id")
    suspend fun getById(id: Int): PictureEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictures: List<PictureEntity>)

    @Query("DELETE FROM Pictures WHERE isFavorite = 0 OR isFavorite = NOT :skipFavorites")
    suspend fun deleteAll(skipFavorites: Boolean)

    @Query("UPDATE Pictures SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun toggleFavoriteFlag(id: Int)
}