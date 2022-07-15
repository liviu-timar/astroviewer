package com.adyen.android.assignment.domain.usecases

import com.adyen.android.assignment.domain.models.AstronomyPicture
import javax.inject.Inject

class SortAstronomyPictureListUseCase @Inject constructor() {

    operator fun invoke(pictureList: List<AstronomyPicture>, sortBy: SortBy): List<AstronomyPicture> {
        return when (sortBy) {
            SortBy.TITLE_ASC -> pictureList.sortedBy { picture -> picture.title }
            SortBy.DATE_DESC -> pictureList.sortedByDescending { picture -> picture.date }
        }
    }
}

enum class SortBy {
    TITLE_ASC, DATE_DESC
}