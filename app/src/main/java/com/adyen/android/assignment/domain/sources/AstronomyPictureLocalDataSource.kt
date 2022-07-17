package com.adyen.android.assignment.domain.sources

import com.adyen.android.assignment.domain.models.AstronomyPicture

interface AstronomyPictureLocalDataSource {

    suspend fun getPictures(count: Int): List<AstronomyPicture>

    suspend fun insertPictures(pictures: List<AstronomyPicture>)

    suspend fun deleteAllPictures()
}