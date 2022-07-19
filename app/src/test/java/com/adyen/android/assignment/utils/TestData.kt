package com.adyen.android.assignment.utils

import com.adyen.android.assignment.data.api.models.AstronomyPictureDto
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.ui.screens.picturelist.models.PictureListItem
import java.time.LocalDate

val testPictureModelList: List<AstronomyPicture>
    get() = pictureModelList.map { it.copy() }

private val pictureModelList = listOf(
    AstronomyPicture(
        title = "Picture 1",
        desc = "Cool first picture",
        date = LocalDate.parse("2022-07-11"),
        url = "http://url1"
    ),
    AstronomyPicture(
        title = "Picture 2",
        desc = "Cool second picture",
        date = LocalDate.parse("2022-08-12"),
        url = "http://url2"
    ),
)

private val pictureDtoList = listOf(
    AstronomyPictureDto(
        serviceVersion = "1",
        title = "Picture 1",
        explanation = "Cool first picture",
        date = LocalDate.parse("2022-07-11"),
        mediaType = "image",
        hdUrl = "http://hdurl1",
        url = "http://url1"
    ),
    AstronomyPictureDto(
        serviceVersion = "1",
        title = "Picture 2",
        explanation = "Cool second picture",
        date = LocalDate.parse("2022-08-12"),
        mediaType = "image",
        hdUrl = "http://hdurl2",
        url = "http://url2"
    )
)

val testPictureDtoList: List<AstronomyPictureDto>
    get() = pictureDtoList.map { it.copy() }

const val testDate = "01 Jan 2022"

private val pictureListItems = listOf(
    PictureListItem(
        id = 0,
        title = "Picture 1",
        date = testDate,
        url = "http://url1"
    ),
    PictureListItem(
        id = 0,
        title = "Picture 2",
        date = testDate,
        url = "http://url2"
    ),
)

val testPictureListItems: List<PictureListItem>
    get() = pictureListItems.map { it.copy() }
