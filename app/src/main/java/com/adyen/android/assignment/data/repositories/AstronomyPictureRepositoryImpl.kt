package com.adyen.android.assignment.data.repositories

import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import com.adyen.android.assignment.domain.sources.AstronomyPictureLocalDataSource
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
import javax.inject.Inject

class AstronomyPictureRepositoryImpl @Inject constructor(
    private val remoteDataSource: AstronomyPictureRemoteDataSource,
    private val localDataSource: AstronomyPictureLocalDataSource,
    // The DAO can be used instead of the local data source as there is no extra logic needed, but let's illustrate having a local ds.
) : AstronomyPictureRepository {

    override suspend fun getPictures(refresh: Boolean, count: Int): List<AstronomyPicture> {
        val isCacheEmpty = localDataSource.getPictures(count).isEmpty()

        if (isCacheEmpty || refresh) {
            if (refresh) localDataSource.deleteAllPictures()
            localDataSource.insertPictures(remoteDataSource.getPictures(count))
        }

        return localDataSource.getPictures(count)
    }
}