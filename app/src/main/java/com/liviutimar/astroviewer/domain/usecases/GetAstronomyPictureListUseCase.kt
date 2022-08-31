package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.models.AstronomyPicture
import com.liviutimar.astroviewer.domain.repositories.AstronomyPictureRepository
import javax.inject.Inject

class GetAstronomyPictureListUseCase @Inject constructor(
    private val pictureRepository: AstronomyPictureRepository,
    private val sortPictureListUseCase: SortAstronomyPictureListUseCase
) {

    suspend operator fun invoke(refresh: Boolean, count: Int, sortBy: SortBy): List<AstronomyPicture> =
        sortPictureListUseCase(pictureRepository.getPictures(refresh = refresh, count = count), sortBy)
}