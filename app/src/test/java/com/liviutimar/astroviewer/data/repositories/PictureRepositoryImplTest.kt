package com.liviutimar.astroviewer.data.repositories

import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import com.liviutimar.astroviewer.domain.sources.PictureLocalDataSource
import com.liviutimar.astroviewer.domain.sources.PictureRemoteDataSource
import com.liviutimar.astroviewer.utils.MainDispatcherRule
import com.liviutimar.astroviewer.utils.testPictureModelList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PictureRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule() // Replace Main dispatcher

    private lateinit var repository: PictureRepository
    private lateinit var mockRemoteDataSource: PictureRemoteDataSource
    private lateinit var mockLocalDataSource: PictureLocalDataSource

    @Before
    fun setUp() {
        mockRemoteDataSource = mock()
        mockLocalDataSource = mock()
        repository = PictureRepositoryImpl(
            mockRemoteDataSource,
            mockLocalDataSource,
            mainDispatcherRule.testDispatcher // Reuse the dispatcher that replaces Main
        )
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun get_refreshTrueAndCount_getsFromRemoteAndWritesToLocal() = runTest { // Takes scheduler from Main
        // Given
        val refresh = true
        val count = 2
        whenever(mockRemoteDataSource.get(anyInt())).thenReturn(testPictureModelList)

        // When
        repository.get(refresh, count)

        // Then
        verify(mockRemoteDataSource).get(count) // Verify behaviour - method calls with specific arg
        verify(mockLocalDataSource).deleteByFavoriteStatus(isFavorite = false)
        verify(mockLocalDataSource).insert(testPictureModelList)
        verify(mockLocalDataSource).getByFavoriteStatus(isFavorite = false)
    }

    @Test
    fun get_refreshFalseAndCount_getsOnlyFromLocal() = runTest {
        val refresh = false
        val count = 2

        repository.get(refresh, count)

        verify(mockRemoteDataSource, never()).get(anyInt())
        verify(mockLocalDataSource, never()).deleteByFavoriteStatus(anyBoolean())
        verify(mockLocalDataSource, never()).insert(anyList())
        verify(mockLocalDataSource).getByFavoriteStatus(isFavorite = false)
    }

    @Test
    fun get_refreshTrueAndCount_returnsPictureList() = runTest {
        val refresh = true
        val count = 2
        whenever(mockLocalDataSource.getByFavoriteStatus(isFavorite = false)).thenReturn(testPictureModelList)

        // When
        val pictures = repository.get(refresh, count)

        // Then
        assertThat(pictures, IsEqual(testPictureModelList)) // Verify state - variable value
    }

    @Test
    fun get_refreshFalseAndCount_returnsPictureList() = runTest {
        val refresh = false
        val count = 2
        whenever(mockLocalDataSource.getByFavoriteStatus(isFavorite = false)).thenReturn(testPictureModelList)

        // When
        val pictures = repository.get(refresh, count)

        // Then
        assertThat(pictures, IsEqual(testPictureModelList))
    }

    @Test
    fun getById_pictureId_getsFromLocalWithSameId() = runTest {
        val pictureId = 1

        repository.getById(pictureId)

        verify(mockLocalDataSource).getById(pictureId)
    }

    @Test
    fun getById_anyId_returnsPicture() = runTest {
        whenever(mockLocalDataSource.getById(anyInt())).thenReturn(testPictureModelList[0])

        val picture = repository.getById(anyInt())

        assertThat(picture, IsEqual(testPictureModelList[0]))
    }
}