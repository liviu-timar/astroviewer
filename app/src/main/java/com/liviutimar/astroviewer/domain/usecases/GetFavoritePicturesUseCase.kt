package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import javax.inject.Inject

class GetFavoritePicturesUseCase @Inject constructor(private val pictureRepository: PictureRepository) {

    suspend operator fun invoke(): List<Picture> = pictureRepository.getFavorites()
}