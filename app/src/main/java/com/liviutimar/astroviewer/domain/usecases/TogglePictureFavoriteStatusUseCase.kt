package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import javax.inject.Inject

class TogglePictureFavoriteStatusUseCase @Inject constructor(
    private val pictureRepository: PictureRepository
) {

    suspend operator fun invoke(pictureId: Int) = pictureRepository.toggleFavoriteStatus(pictureId)
}