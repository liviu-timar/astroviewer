package com.liviutimar.astroviewer.domain.usecases

import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import com.liviutimar.astroviewer.utils.testPictureModelList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetPictureDetailsUseCaseTest {

    private lateinit var useCase: GetPictureDetailsUseCase
    private lateinit var mockPictureRepository: PictureRepository

    @Before
    fun setUp() {
        mockPictureRepository = mock()
        useCase = GetPictureDetailsUseCase(mockPictureRepository)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun invoke_pictureId_callsRepoWithSameId() = runTest {
        val pictureId = 1

        useCase(pictureId)

        verify(mockPictureRepository).getById(pictureId)
    }

    @Test
    fun invoke_anyId_returnsPicture() = runTest {
        whenever(mockPictureRepository.getById(anyInt())).thenReturn(testPictureModelList[0])

        val picture = useCase(anyInt())

        assertThat(picture, IsEqual(testPictureModelList[0]))
    }
}