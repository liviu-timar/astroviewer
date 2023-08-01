package com.liviutimar.astroviewer.utils

import com.liviutimar.astroviewer.data.api.dto.PictureDto
import com.liviutimar.astroviewer.domain.models.Picture

val testPictureModelList: List<Picture>
    get() = pictureModelList.map { it.copy() }

private val pictureModelList = listOf(
    Picture(
        title = "Picture 1",
        desc = "Cool first picture",
        date = "11 Jul 2022",
        url = "http://url1"
    ),
    Picture(
        title = "Picture 2",
        desc = "Cool second picture",
        date = "12 Aug 2022",
        url = "http://url2"
    ),
)

val testPictureDtoList: List<PictureDto>
    get() = pictureDtoList.map { it.copy() }

private val pictureDtoList = listOf(
    PictureDto(
        serviceVersion = "1",
        title = "Picture 1",
        explanation = "Cool first picture",
        date = "2022-07-11",
        mediaType = "image",
        hdUrl = "http://hdurl1",
        url = "http://url1"
    ),
    PictureDto(
        serviceVersion = "1",
        title = "Picture 2",
        explanation = "Cool second picture",
        date = "2022-08-12",
        mediaType = "image",
        hdUrl = "http://hdurl2",
        url = "http://url2"
    )
)
