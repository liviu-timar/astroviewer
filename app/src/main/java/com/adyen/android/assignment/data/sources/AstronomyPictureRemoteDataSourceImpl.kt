package com.adyen.android.assignment.data.sources

import com.adyen.android.assignment.data.remote.PlanetaryService
import com.adyen.android.assignment.data.remote.models.AstronomyPictureDto
import com.adyen.android.assignment.data.remote.models.asDomainModel
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
import javax.inject.Inject

class AstronomyPictureRemoteDataSourceImpl @Inject constructor(
    private val planetaryService: PlanetaryService
) : AstronomyPictureRemoteDataSource {

    override suspend fun getPictures(count: Int): List<AstronomyPicture> {
        return planetaryService.getPictures(count).body()
            ?.filter { dto -> dto.mediaType == "image" }
            ?.map(AstronomyPictureDto::asDomainModel)
            ?: emptyList()
    }
}