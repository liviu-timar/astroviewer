package com.liviutimar.astroviewer.domain.repositories

import com.liviutimar.astroviewer.domain.models.AstronomyPicture

interface AstronomyPictureRepository {

    suspend fun getPictures(refresh: Boolean, count: Int): List<AstronomyPicture>

    suspend fun getPicture(id: Int): AstronomyPicture
}