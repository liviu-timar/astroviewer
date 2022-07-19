package com.adyen.android.assignment.data.api.models

import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.squareup.moshi.Json
import java.time.LocalDate

data class AstronomyPictureDto(
    @Json(name = "service_version") val serviceVersion: String,
    val title: String,
    val explanation: String,
    val date: LocalDate,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "hdurl") val hdUrl: String?,
    val url: String,
)

fun AstronomyPictureDto.asDomainModel() = AstronomyPicture(
    title = title,
    desc = explanation,
    date = date,
    url = url
)