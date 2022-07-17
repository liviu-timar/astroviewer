package com.adyen.android.assignment.domain.repositories

import com.adyen.android.assignment.domain.models.AstronomyPicture

interface AstronomyPictureRepository {

    suspend fun getPictures(refresh: Boolean, count: Int): List<AstronomyPicture>
}