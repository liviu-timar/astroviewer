package com.adyen.android.assignment.data.sources

import com.adyen.android.assignment.data.api.PlanetaryService
import com.adyen.android.assignment.data.api.models.AstronomyPictureDto
import com.adyen.android.assignment.data.api.models.asDomainModel
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
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