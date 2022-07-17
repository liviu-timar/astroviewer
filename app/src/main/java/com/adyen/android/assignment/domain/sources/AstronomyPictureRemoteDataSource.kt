package com.adyen.android.assignment.domain.sources

import com.adyen.android.assignment.domain.models.AstronomyPicture

interface AstronomyPictureRemoteDataSource {

    suspend fun getPictures(count: Int): List<AstronomyPicture>
}