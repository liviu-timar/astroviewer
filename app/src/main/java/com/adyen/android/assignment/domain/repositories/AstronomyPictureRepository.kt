package com.adyen.android.assignment.domain.repositories

import com.adyen.android.assignment.domain.models.AstronomyPicture

interface AstronomyPictureRepository {

    suspend fun getPictures(count: Int): List<AstronomyPicture>
}