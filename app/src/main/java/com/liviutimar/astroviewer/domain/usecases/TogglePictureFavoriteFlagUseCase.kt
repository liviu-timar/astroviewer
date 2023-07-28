package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import javax.inject.Inject

class TogglePictureFavoriteFlagUseCase @Inject constructor(
    private val pictureRepository: PictureRepository
) {

    suspend operator fun invoke(id: Int) = pictureRepository.toggleFavoriteFlag(id)
}