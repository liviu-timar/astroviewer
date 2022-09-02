package com.liviutimar.astroviewer.ui.screens.picturelist

import com.liviutimar.astroviewer.domain.usecases.FormatDateUseCase
import com.liviutimar.astroviewer.domain.usecases.GetAstronomyPictureListUseCase
import com.liviutimar.astroviewer.domain.usecases.SortBy
import com.liviutimar.astroviewer.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AstronomyPictureListViewModelTest {

    // Replaces Dispatchers.Main with a testing dispatcher because tests do not have a main Looper.
    // Used by viewModelScope.launch().
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: AstronomyPictureListViewModel
    private lateinit var mockGetAstronomyPictureListUseCase: GetAstronomyPictureListUseCase
    private lateinit var mockFormatDateUseCase: FormatDateUseCase

    @Before
    fun setUp() {
        mockGetAstronomyPictureListUseCase = mock()
        mockFormatDateUseCase = mock()
        viewModel = AstronomyPictureListViewModel(mockGetAstronomyPictureListUseCase, mockFormatDateUseCase)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun picturesFlow_pictureModelList_returnsPictureListItems() = runTest {
        // Given
        whenever(mockGetAstronomyPictureListUseCase.invoke(any(), any(), any())).thenReturn(testPictureModelList)
        whenever(mockFormatDateUseCase.invoke(any())).thenReturn(testDate)

        // When
        viewModel.getPictureList()
        val pictures = viewModel.uiState.value.pictures

        // Then
        assertThat(pictures, IsEqual(testPictureListItems)) // Verify state - variable value
    }

    @Test
    fun getPictureList_refreshAndCountAndSortArgs_callsUseCaseWithSameArgs() = runTest {
        val refresh = true
        val count = 2
        val sortBy = SortBy.TITLE_ASC
        whenever(mockGetAstronomyPictureListUseCase.invoke(any(), any(), any())).thenReturn(testPictureModelList)
        whenever(mockFormatDateUseCase.invoke(any())).thenReturn(testDate)

        viewModel.getPictureList(refresh, count, sortBy)

        verify(mockGetAstronomyPictureListUseCase).invoke(refresh, count, sortBy) // Verify behaviour - method calls
    }
}