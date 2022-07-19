package com.adyen.android.assignment.data.repositories

import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import com.adyen.android.assignment.domain.sources.AstronomyPictureLocalDataSource
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
import com.adyen.android.assignment.utils.testPictureList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AstronomyPictureRepositoryImplTest {

    private lateinit var repository: AstronomyPictureRepository
    private lateinit var mockRemoteDataSource: AstronomyPictureRemoteDataSource
    private lateinit var mockLocalDataSource: AstronomyPictureLocalDataSource

    @Before
    fun setUp() {
        mockRemoteDataSource = mock()
        mockLocalDataSource = mock()
        repository = AstronomyPictureRepositoryImpl(mockRemoteDataSource, mockLocalDataSource)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun getPictures_refreshTrueAndCount_getsFromRemoteAndWritesToLocal() = runTest {
        // Given
        val refresh = true
        val count = 2
        whenever(mockRemoteDataSource.getPictures(anyInt())).thenReturn(testPictureList)

        // When
        repository.getPictures(refresh, count)

        // Then
        verify(mockRemoteDataSource).getPictures(count) // Verify behaviour - method calls with specific arg
        verify(mockLocalDataSource).deleteAllPictures()
        verify(mockLocalDataSource).insertPictures(testPictureList)
        verify(mockLocalDataSource).getPictures(count)
    }

    @Test
    fun getPictures_refreshFalseAndCount_getsOnlyFromLocal() = runTest {
        val refresh = false
        val count = 2

        repository.getPictures(refresh, count)

        verify(mockRemoteDataSource, never()).getPictures(anyInt())
        verify(mockLocalDataSource, never()).deleteAllPictures()
        verify(mockLocalDataSource, never()).insertPictures(anyList())
        verify(mockLocalDataSource).getPictures(count)
    }

    @Test
    fun getPictures_refreshTrueAndCount_returnsPictureList() = runTest {
        val refresh = true
        val count = 2
        whenever(mockLocalDataSource.getPictures(anyInt())).thenReturn(testPictureList)

        // When
        val pictures = repository.getPictures(refresh, count)

        // Then
        assertThat(pictures, IsEqual(testPictureList)) // Verify state - variable value
    }

    @Test
    fun getPictures_refreshFalseAndCount_returnsPictureList() = runTest {
        val refresh = false
        val count = 2
        whenever(mockLocalDataSource.getPictures(anyInt())).thenReturn(testPictureList)

        // When
        val pictures = repository.getPictures(refresh, count)

        // Then
        assertThat(pictures, IsEqual(testPictureList))
    }

    @Test
    fun getPicture_pictureId_getsFromLocalWithSameId() = runTest {
        val pictureId = 1

        repository.getPicture(pictureId)

        verify(mockLocalDataSource).getPicture(pictureId)
    }

    @Test
    fun getPicture_anyId_returnsPicture() = runTest {
        whenever(mockLocalDataSource.getPicture(anyInt())).thenReturn(testPictureList[0])

        val picture = repository.getPicture(anyInt())

        assertThat(picture, IsEqual(testPictureList[0]))
    }
}