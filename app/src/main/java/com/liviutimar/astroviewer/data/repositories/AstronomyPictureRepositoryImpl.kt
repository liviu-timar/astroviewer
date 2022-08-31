package com.liviutimar.astroviewer.data.repositories

import com.liviutimar.astroviewer.domain.models.AstronomyPicture
import com.liviutimar.astroviewer.domain.repositories.AstronomyPictureRepository
import com.liviutimar.astroviewer.domain.sources.AstronomyPictureLocalDataSource
import com.liviutimar.astroviewer.domain.sources.AstronomyPictureRemoteDataSource
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
            val remotePictures = remoteDataSource.getPictures(count)
            localDataSource.deleteAllPictures()
            localDataSource.insertPictures(remotePictures)
        }

        localDataSource.getPictures(count)
    }

    override suspend fun getPicture(id: Int): AstronomyPicture = withContext(ioDispatcher) { localDataSource.getPicture(id) }
}