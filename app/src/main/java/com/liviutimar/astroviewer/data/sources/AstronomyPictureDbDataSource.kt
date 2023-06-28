package com.liviutimar.astroviewer.data.sources

import com.liviutimar.astroviewer.data.db.dao.AstronomyPictureDao
import com.liviutimar.astroviewer.data.db.entities.AstronomyPictureEntity
import com.liviutimar.astroviewer.data.db.entities.asDomainModel
import com.liviutimar.astroviewer.data.db.entities.asEntity
import com.liviutimar.astroviewer.domain.models.AstronomyPicture
import com.liviutimar.astroviewer.domain.sources.AstronomyPictureLocalDataSource
import javax.inject.Inject

class AstronomyPictureDbDataSource @Inject constructor(
    private val astronomyPictureDao: AstronomyPictureDao
) : AstronomyPictureLocalDataSource {

    override suspend fun getPictures(count: Int): List<AstronomyPicture> =
        astronomyPictureDao.get(count = count).map(AstronomyPictureEntity::asDomainModel)

    override suspend fun getPicture(id: Int): AstronomyPicture =
        astronomyPictureDao.getById(id = id).asDomainModel()

    override suspend fun insertPictures(pictures: List<AstronomyPicture>) =
        astronomyPictureDao.insert(pictures.map(AstronomyPicture::asEntity))

    override suspend fun deleteAllPictures() = astronomyPictureDao.deleteAll()
}