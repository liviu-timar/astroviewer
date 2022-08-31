package com.liviutimar.astroviewer.data.sources

import com.liviutimar.astroviewer.data.api.PlanetaryService
import com.liviutimar.astroviewer.utils.testPictureDtoList
import com.liviutimar.astroviewer.utils.testPictureModelList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class AstronomyPictureApiDataSourceTest {

    // SUT
    private lateinit var dataSource: AstronomyPictureApiDataSource
    // Dependencies
    private lateinit var mockPlanetaryService: com.liviutimar.astroviewer.data.api.PlanetaryService

    @Before
    fun setUp() {
        mockPlanetaryService = mock()
        dataSource = AstronomyPictureApiDataSource(mockPlanetaryService)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun getPictures_count_callsService() = runTest {
        val count = 2
        whenever(mockPlanetaryService.getPictures(anyInt())).thenReturn(Response.success(testPictureDtoList))

        dataSource.getPictures(count)

        verify(mockPlanetaryService).getPictures(count)
    }

    @Test
    fun getPictures_pictureDtoList_returnsPictureModelList() = runTest {
        // Given
        whenever(mockPlanetaryService.getPictures(anyInt())).thenReturn(Response.success(testPictureDtoList))

        // When
        val pictures = dataSource.getPictures(2)

        // Then
        assertThat(pictures, IsEqual(testPictureModelList)) // Verify state - returned data
    }
}