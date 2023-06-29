package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import javax.inject.Inject

class GetPictureListUseCase @Inject constructor(
    private val pictureRepository: PictureRepository,
    private val sortPictureListUseCase: SortPictureListUseCase
) {

    suspend operator fun invoke(refresh: Boolean, count: Int, sortBy: SortBy): List<Picture> =
        sortPictureListUseCase(pictureRepository.getPictures(refresh = refresh, count = count), sortBy)
}