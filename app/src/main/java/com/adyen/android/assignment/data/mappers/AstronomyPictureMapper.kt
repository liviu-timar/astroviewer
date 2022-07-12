package com.adyen.android.assignment.data.mappers

import com.adyen.android.assignment.data.api.dto.AstronomyPictureDto
import com.adyen.android.assignment.domain.models.AstronomyPicture
import javax.inject.Inject

class AstronomyPictureMapper @Inject constructor() {

    fun map(dto: AstronomyPictureDto): AstronomyPicture {
        return AstronomyPicture(
            title = dto.title,
            desc = dto.explanation,
            date = dto.date,
            url = dto.url
        )
    }
}