package com.adyen.android.assignment.domain.usecases

import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
import com.adyen.android.assignment.utils.testPictureModelList
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
class GetAstronomyPictureDetailsUseCaseTest {

    private lateinit var useCase: GetAstronomyPictureDetailsUseCase
    private lateinit var mockPictureRepository: AstronomyPictureRepository

    @Before
    fun setUp() {
        mockPictureRepository = mock()
        useCase = GetAstronomyPictureDetailsUseCase(mockPictureRepository)
    }

    // Test method naming: subjectUnderTest_input_expectedResult
    @Test
    fun invoke_pictureId_callsRepoWithSameId() = runTest {
        val pictureId = 1

        useCase(pictureId)

        verify(mockPictureRepository).getPicture(pictureId)
    }

    @Test
    fun invoke_anyId_returnsPicture() = runTest {
        whenever(mockPictureRepository.getPicture(anyInt())).thenReturn(testPictureModelList[0])

        val picture = useCase(anyInt())

        assertThat(picture, IsEqual(testPictureModelList[0]))
    }
}