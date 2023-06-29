package com.liviutimar.astroviewer.data.sources

import com.liviutimar.astroviewer.data.api.PlanetaryService
import com.liviutimar.astroviewer.data.api.dto.PictureDto
import com.liviutimar.astroviewer.data.api.dto.asDomainModel
import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.sources.PictureRemoteDataSource
import javax.inject.Inject

class PictureApiDataSource @Inject constructor(
    private val planetaryService: PlanetaryService
) : PictureRemoteDataSource {

    override suspend fun getPictures(count: Int): List<Picture> {
        return planetaryService.getPictures(count).body()
            ?.filter { dto -> dto.mediaType == "image" }
            ?.map(PictureDto::asDomainModel)
            ?: emptyList()
    }
}