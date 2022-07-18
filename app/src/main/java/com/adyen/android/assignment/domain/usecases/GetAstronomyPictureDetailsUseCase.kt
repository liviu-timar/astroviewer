package com.adyen.android.assignment.domain.usecases

import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import javax.inject.Inject

class GetAstronomyPictureDetailsUseCase @Inject constructor(
    private val pictureRepository: AstronomyPictureRepository
) {

    suspend operator fun invoke(pictureId: Int): AstronomyPicture =
        pictureRepository.getPicture(id = pictureId)
}