package com.adyen.android.assignment.domain.sources

import com.adyen.android.assignment.domain.models.AstronomyPicture

interface AstronomyPictureDataSource {

    suspend fun getPictures(count: Int): List<AstronomyPicture>
}