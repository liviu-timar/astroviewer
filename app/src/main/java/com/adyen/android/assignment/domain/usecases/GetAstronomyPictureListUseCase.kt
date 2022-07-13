package com.adyen.android.assignment.domain.usecases

import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import javax.inject.Inject

class GetAstronomyPictureListUseCase @Inject constructor(private val repository: AstronomyPictureRepository) {

    suspend operator fun invoke(count: Int): List<AstronomyPicture> = repository.getPictures(count)
}