package com.adyen.android.assignment.data.repositories

import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class AstronomyPictureRepositoryImplTest {

    private lateinit var repository: AstronomyPictureRepository
    private lateinit var mockAstronomyPictureDataSource: AstronomyPictureRemoteDataSource

    private val testPictureList = listOf(
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
        mockAstronomyPictureDataSource = mock()
        repository = AstronomyPictureRepositoryImpl(mockAstronomyPictureDataSource)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun getPictures_pictureList_returnsPictureList() = runTest {
        // Given
        whenever(mockAstronomyPictureDataSource.getPictures(any())).thenReturn(testPictureList)

        // When
        val pictures = repository.getPictures(2)

        // Then
        assertThat(pictures, IsEqual(testPictureList)) // Verify state - variable value
    }
}