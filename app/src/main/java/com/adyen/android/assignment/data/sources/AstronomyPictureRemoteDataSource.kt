package com.adyen.android.assignment.data.sources

import com.adyen.android.assignment.data.api.PlanetaryService
import com.adyen.android.assignment.data.mappers.AstronomyPictureMapper
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.sources.AstronomyPictureDataSource
import javax.inject.Inject

class AstronomyPictureRemoteDataSource @Inject constructor(
    private val planetaryService: PlanetaryService,
    private val astronomyPictureMapper: AstronomyPictureMapper
) : AstronomyPictureDataSource {

    override suspend fun getPictures(count: Int): List<AstronomyPicture> {
        return planetaryService.getPictures().body()?.let { dtoList ->
            dtoList
                .filter { dto -> dto.mediaType == "image" }
                .map { dto -> astronomyPictureMapper.map(dto) }
        } ?: emptyList()
    }
}