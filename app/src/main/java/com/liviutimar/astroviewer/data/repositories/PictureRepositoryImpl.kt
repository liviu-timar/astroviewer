package com.liviutimar.astroviewer.data.repositories

import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import com.liviutimar.astroviewer.domain.sources.PictureLocalDataSource
import com.liviutimar.astroviewer.domain.sources.PictureLocalDataSource.*
import com.liviutimar.astroviewer.domain.sources.PictureRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val remoteDataSource: PictureRemoteDataSource,
    private val localDataSource: PictureLocalDataSource,
    // The PictureDao interface can be used instead of LocalDataSource
    // as there is no extra logic in the data source,
    // but let's illustrate having a local data source.
    private val ioDispatcher: CoroutineDispatcher
) : PictureRepository {

    override suspend fun getPictures(refresh: Boolean, count: Int): List<Picture> = withContext(ioDispatcher) {
        if (refresh) {
            val remotePictures = remoteDataSource.getPictures(count)
            localDataSource.deleteAllPictures(skipFavorites = true)
            localDataSource.insertPictures(remotePictures)
        }

        localDataSource.getPictures(isFavorite = false)
    }

    override suspend fun getPicture(id: Int): Picture = withContext(ioDispatcher) { localDataSource.getPicture(id) }

    override suspend fun toggleFavoriteFlag(id: Int) = withContext(ioDispatcher) { localDataSource.toggleFavoriteFlag(id) }
}