package com.liviutimar.astroviewer.ui.screens.picturelist

import com.liviutimar.astroviewer.domain.usecases.GetPictureListUseCase
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
class PictureListViewModelTest {

    // Replaces Dispatchers.Main with a testing dispatcher because tests do not have a main Looper.
    // Used by viewModelScope.launch().
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: PictureListViewModel
    private lateinit var mockGetPictureListUseCase: GetPictureListUseCase

    @Before
    fun setUp() {
        mockGetPictureListUseCase = mock()
        viewModel = PictureListViewModel(mockGetPictureListUseCase)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun uiState_pictureModelList_containsSamePictureModelList() = runTest {
        // Given
        whenever(mockGetPictureListUseCase.invoke(any(), any(), any())).thenReturn(testPictureModelList)

        // When
        viewModel.getPictureList(refresh = true)
        val pictures = viewModel.uiState.value.pictures

        // Then
        assertThat(pictures, IsEqual(testPictureModelList)) // Verify state - variable value
    }

    @Test
    fun getPictureList_refreshAndCountAndSortArgs_callsUseCaseWithSameArgs() = runTest {
        val refresh = true
        val count = 2
        val sortBy = SortBy.TITLE_ASC
        whenever(mockGetPictureListUseCase.invoke(any(), any(), any())).thenReturn(testPictureModelList)

        viewModel.getPictureList(refresh, count, sortBy)

        verify(mockGetPictureListUseCase).invoke(refresh, count, sortBy) // Verify behaviour - method calls
    }
}