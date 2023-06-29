package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.models.Picture
import javax.inject.Inject

class SortPictureListUseCase @Inject constructor() {

    operator fun invoke(pictureList: List<Picture>, sortBy: SortBy): List<Picture> {
        return when (sortBy) {
            SortBy.TITLE_ASC -> pictureList.sortedBy { picture -> picture.title }
            SortBy.DATE_DESC -> pictureList.sortedByDescending { picture -> picture.date }
        }
    }
}

enum class SortBy {
    TITLE_ASC, DATE_DESC
}