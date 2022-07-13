package com.adyen.android.assignment.data.repositories

import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import com.adyen.android.assignment.domain.sources.AstronomyPictureDataSource
import javax.inject.Inject

class AstronomyPictureRepositoryImpl @Inject constructor(
    private val remoteDataSource: AstronomyPictureDataSource
) : AstronomyPictureRepository {

    override suspend fun getPictures(count: Int) = remoteDataSource.getPictures(count)
}