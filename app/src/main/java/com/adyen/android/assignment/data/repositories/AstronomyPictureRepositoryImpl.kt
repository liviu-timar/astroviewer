package com.adyen.android.assignment.data.repositories

import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import com.adyen.android.assignment.domain.sources.AstronomyPictureLocalDataSource
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AstronomyPictureRepositoryImpl @Inject constructor(
    private val remoteDataSource: AstronomyPictureRemoteDataSource,
    private val localDataSource: AstronomyPictureLocalDataSource,
    // The PictureDao interface can be used instead of LocalDataSource
    // as there is no extra logic in the data source,
    // but let's illustrate having a local data source.
    private val ioDispatcher: CoroutineDispatcher
) : AstronomyPictureRepository {

    override suspend fun getPictures(refresh: Boolean, count: Int): List<AstronomyPicture> = withContext(ioDispatcher) {
        if (refresh) {
            val remotePictures = try {
                remoteDataSource.getPictures(count)
            } catch (e: Exception) {
                localDataSource.getPictures(count)
            }

            localDataSource.deleteAllPictures()
            localDataSource.insertPictures(remotePictures)
        }

        localDataSource.getPictures(count)
    }

    override suspend fun getPicture(id: Int): AstronomyPicture = withContext(ioDispatcher) { localDataSource.getPicture(id) }
}