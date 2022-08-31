package com.liviutimar.astroviewer.domain.sources

import com.liviutimar.astroviewer.domain.models.AstronomyPicture

interface AstronomyPictureLocalDataSource {

    suspend fun getPictures(count: Int): List<AstronomyPicture>

    suspend fun getPicture(id: Int): AstronomyPicture

    suspend fun insertPictures(pictures: List<AstronomyPicture>)

    suspend fun deleteAllPictures()
}