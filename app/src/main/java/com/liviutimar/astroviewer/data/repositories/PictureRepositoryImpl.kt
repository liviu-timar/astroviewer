package com.liviutimar.astroviewer.data.repositories

import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import com.liviutimar.astroviewer.domain.sources.PictureLocalDataSource
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

    override suspend fun get(refresh: Boolean, count: Int): List<Picture> = withContext(ioDispatcher) {
        if (refresh) {
            val remotePictures = remoteDataSource.get(count)
            localDataSource.deleteByFavoriteStatus(isFavorite = false)
            localDataSource.insert(remotePictures)
        }

        localDataSource.getByFavoriteStatus(isFavorite = false)
    }

    override suspend fun getById(id: Int): Picture = withContext(ioDispatcher) {
        localDataSource.getById(id)
    }

    override suspend fun getFavorites() = withContext(ioDispatcher) {
        localDataSource.getByFavoriteStatus(isFavorite = true)
    }

    override suspend fun toggleFavoriteStatus(pictureId: Int) = withContext(ioDispatcher) {
        localDataSource.toggleFavoriteStatus(pictureId)
    }
}