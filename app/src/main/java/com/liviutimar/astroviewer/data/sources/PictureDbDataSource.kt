package com.liviutimar.astroviewer.data.sources

import com.liviutimar.astroviewer.data.db.dao.PictureDao
import com.liviutimar.astroviewer.data.db.entities.PictureEntity
import com.liviutimar.astroviewer.data.db.entities.asDomainModel
import com.liviutimar.astroviewer.data.db.entities.asEntity
import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.sources.PictureLocalDataSource
import javax.inject.Inject

class PictureDbDataSource @Inject constructor(
    private val pictureDao: PictureDao
) : PictureLocalDataSource {

    override suspend fun getById(id: Int): Picture =
        pictureDao.getById(id).asDomainModel()

    override suspend fun getByFavoriteStatus(isFavorite: Boolean): List<Picture> =
        pictureDao.getByFavoriteStatus(isFavorite).map(PictureEntity::asDomainModel)

    override suspend fun insert(pictures: List<Picture>) =
        pictureDao.insert(pictures.map(Picture::asEntity))

    override suspend fun deleteByFavoriteStatus(isFavorite: Boolean) =
        pictureDao.deleteByFavoriteStatus(isFavorite)

    override suspend fun toggleFavoriteStatus(pictureId: Int) =
        pictureDao.toggleFavoriteStatus(pictureId)
}