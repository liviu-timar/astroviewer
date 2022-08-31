package com.liviutimar.astroviewer.domain.sources

import com.liviutimar.astroviewer.domain.models.AstronomyPicture

interface AstronomyPictureRemoteDataSource {

    suspend fun getPictures(count: Int): List<AstronomyPicture>
}