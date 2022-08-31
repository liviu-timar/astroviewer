package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.models.AstronomyPicture
import com.liviutimar.astroviewer.domain.repositories.AstronomyPictureRepository
import javax.inject.Inject

class GetAstronomyPictureDetailsUseCase @Inject constructor(
    private val pictureRepository: AstronomyPictureRepository
) {

    suspend operator fun invoke(pictureId: Int): AstronomyPicture =
        pictureRepository.getPicture(id = pictureId)
}