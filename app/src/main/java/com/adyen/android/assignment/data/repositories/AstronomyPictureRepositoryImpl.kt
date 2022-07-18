package com.adyen.android.assignment.data.repositories

import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import com.adyen.android.assignment.domain.sources.AstronomyPictureLocalDataSource
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
import javax.inject.Inject

class AstronomyPictureRepositoryImpl @Inject constructor(
    private val remoteDataSource: AstronomyPictureRemoteDataSource,
    private val localDataSource: AstronomyPictureLocalDataSource,
    // The PictureDao interface can be used instead of LocalDataSource
    // as there is no extra logic in the data source,
    // but let's illustrate having a local data source.
) : AstronomyPictureRepository {

    override suspend fun getPictures(refresh: Boolean, count: Int): List<AstronomyPicture> {
        if (refresh) {
            val remotePictures = try {
                remoteDataSource.getPictures(count)
            } catch (e: Exception) {
                return localDataSource.getPictures(count)
            }

            localDataSource.deleteAllPictures()
            localDataSource.insertPictures(remotePictures)
        }

        return localDataSource.getPictures(count)
    }

    override suspend fun getPicture(id: Int): AstronomyPicture = localDataSource.getPicture(id)
}