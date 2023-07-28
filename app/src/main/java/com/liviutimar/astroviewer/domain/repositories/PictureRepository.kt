package com.liviutimar.astroviewer.domain.repositories

import com.liviutimar.astroviewer.domain.models.Picture

interface PictureRepository {

    suspend fun getPictures(refresh: Boolean, count: Int): List<Picture>

    suspend fun getPicture(id: Int): Picture

    suspend fun toggleFavoriteFlag(id: Int)
}