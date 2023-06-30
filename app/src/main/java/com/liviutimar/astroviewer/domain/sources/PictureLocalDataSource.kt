package com.liviutimar.astroviewer.domain.sources

import com.liviutimar.astroviewer.domain.models.Picture

interface PictureLocalDataSource {

    suspend fun getPictures(count: Int): List<Picture>

    suspend fun getPicture(id: Int): Picture

    suspend fun insertPictures(pictures: List<Picture>)

    suspend fun deleteAllPictures()
}