package com.liviutimar.astroviewer.domain.repositories

import com.liviutimar.astroviewer.domain.models.Picture

interface PictureRepository {

    suspend fun get(refresh: Boolean, count: Int): List<Picture>

    suspend fun getById(id: Int): Picture

    suspend fun getFavorites(): List<Picture>

    suspend fun toggleFavoriteStatus(pictureId: Int)
}