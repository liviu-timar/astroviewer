package com.adyen.android.assignment.data.mappers

import com.adyen.android.assignment.data.api.dto.AstronomyPictureDto
import com.adyen.android.assignment.domain.models.AstronomyPicture
import org.hamcrest.core.IsEqual
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before

import org.junit.Test
import java.time.LocalDate

class AstronomyPictureMapperTest {

    private lateinit var mapper: AstronomyPictureMapper

    private val testPictureDto = AstronomyPictureDto(
        serviceVersion = "1",
        title = "Picture 1",
        explanation = "Cool first picture",
        date = LocalDate.parse("2022-07-11"),
        mediaType = "image",
        hdUrl = "http://hdurl1",
        url = "http://url1"
    )
    private val testPictureModel = AstronomyPicture(
        title = "Picture 1",
        desc = "Cool first picture",
        date = LocalDate.parse("2022-07-11"),
        url = "http://url1"
    )

    @Before
    fun setUp() {
        mapper = AstronomyPictureMapper()
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun map_pictureDto_pictureModel() {
        val picture = mapper.map(testPictureDto)

        assertThat(picture, IsEqual(testPictureModel))
    }
}