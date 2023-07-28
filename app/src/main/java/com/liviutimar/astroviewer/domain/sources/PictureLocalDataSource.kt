package com.liviutimar.astroviewer.domain.sources

import com.liviutimar.astroviewer.domain.models.Picture

interface PictureLocalDataSource {

    suspend fun getById(id: Int): Picture

    suspend fun getByFavoriteStatus(isFavorite: Boolean): List<Picture>

    suspend fun insert(pictures: List<Picture>)

    suspend fun deleteByFavoriteStatus(isFavorite: Boolean)

    suspend fun toggleFavoriteStatus(pictureId: Int)
}