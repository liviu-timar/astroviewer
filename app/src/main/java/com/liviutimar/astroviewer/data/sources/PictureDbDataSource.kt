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

    override suspend fun getPictures(isFavorite: Boolean): List<Picture> =
        pictureDao.get(isFavorite).map(PictureEntity::asDomainModel)

    override suspend fun getPicture(id: Int): Picture =
        pictureDao.getById(id = id).asDomainModel()

    override suspend fun insertPictures(pictures: List<Picture>) =
        pictureDao.insert(pictures.map(Picture::asEntity))

    override suspend fun deleteAllPictures(skipFavorites: Boolean) =
        pictureDao.deleteAll(skipFavorites = skipFavorites)

    override suspend fun toggleFavoriteFlag(id: Int) = pictureDao.toggleFavoriteFlag(id)
}