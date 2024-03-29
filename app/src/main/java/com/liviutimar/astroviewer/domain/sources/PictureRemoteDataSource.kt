package com.liviutimar.astroviewer.domain.sources

import com.liviutimar.astroviewer.domain.models.Picture

interface PictureRemoteDataSource {

    suspend fun get(count: Int): List<Picture>
}