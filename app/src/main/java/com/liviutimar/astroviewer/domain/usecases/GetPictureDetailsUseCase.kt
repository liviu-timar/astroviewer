package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import javax.inject.Inject

class GetPictureDetailsUseCase @Inject constructor(
    private val pictureRepository: PictureRepository
) {

    suspend operator fun invoke(pictureId: Int): Picture =
        pictureRepository.getById(id = pictureId)
}