package com.liviutimar.astroviewer.data.sources

import com.liviutimar.astroviewer.data.api.PlanetaryService
import com.liviutimar.astroviewer.data.api.models.AstronomyPictureDto
import com.liviutimar.astroviewer.data.api.models.asDomainModel
import com.liviutimar.astroviewer.domain.models.AstronomyPicture
import com.liviutimar.astroviewer.domain.sources.AstronomyPictureRemoteDataSource
import javax.inject.Inject

class AstronomyPictureApiDataSource @Inject constructor(
    private val planetaryService: PlanetaryService
) : AstronomyPictureRemoteDataSource {

    override suspend fun getPictures(count: Int): List<AstronomyPicture> {
        return planetaryService.getPictures(count).body()
            ?.filter { dto -> dto.mediaType == "image" }
            ?.map(AstronomyPictureDto::asDomainModel)
            ?: emptyList()
    }
}