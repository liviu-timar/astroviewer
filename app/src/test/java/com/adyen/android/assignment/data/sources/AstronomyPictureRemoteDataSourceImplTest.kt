package com.adyen.android.assignment.data.sources

import com.adyen.android.assignment.data.remote.PlanetaryService
import com.adyen.android.assignment.data.remote.models.AstronomyPictureDto
import com.adyen.android.assignment.domain.models.AstronomyPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class AstronomyPictureRemoteDataSourceImplTest {

    // SUT
    private lateinit var dataSource: AstronomyPictureRemoteDataSourceImpl
    // Dependencies
    private lateinit var mockPlanetaryService: PlanetaryService
    // Test Data
    private val testPictureDtoList = listOf(
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
    private val testPictureModelList = listOf(
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

    @Before
    fun setUp() {
        mockPlanetaryService = mock()
        dataSource = AstronomyPictureRemoteDataSourceImpl(mockPlanetaryService)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun getPictures_pictureDtoList_pictureModelList() = runTest {
        // Given
        whenever(mockPlanetaryService.getPictures()).thenReturn(Response.success(testPictureDtoList))

        // When
        val pictures = dataSource.getPictures(2)

        // Then
        assertThat(pictures, IsEqual(testPictureModelList)) // Verify state - returned data
    }
}