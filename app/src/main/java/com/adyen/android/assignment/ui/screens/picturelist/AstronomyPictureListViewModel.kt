package com.adyen.android.assignment.ui.screens.picturelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.domain.usecases.FormatDateUseCase
import com.adyen.android.assignment.domain.usecases.GetAstronomyPictureListUseCase
import com.adyen.android.assignment.domain.usecases.SortBy
import com.squareup.moshi.JsonDataException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureListViewModel @Inject constructor(
    private val getAstronomyPictureListUseCase: GetAstronomyPictureListUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModel() {

    var isDataFirstLoad: Boolean = true
    var sortPicturesBy = SortBy.DATE_DESC

    private val _uiState = MutableStateFlow(PictureListUiState())
    val uiState = _uiState.asStateFlow()

    fun getPictureList(refresh: Boolean = true, count: Int, sortBy: SortBy = SortBy.DATE_DESC) {
        viewModelScope.launch {
            if (refresh) _uiState.update { PictureListUiState(isLoadingPictures = true) }

            try {
                val pictures = getAstronomyPictureListUseCase(refresh, count, sortBy)
                _uiState.update {
                    PictureListUiState(
                        pictures = pictures.map { picture ->
                            PictureListItemUiState(
                                id = picture.id,
                                title = picture.title,
                                date = formatDateUseCase(picture.date),
                                url = picture.url
                            )
                        }
                    )
                }
            } catch (e: IOException) { // Domain or data layer should catch these exceptions and throw custom ones for the UI layer to handle
                _uiState.update { PictureListUiState(error = Error(isNetworkError = true))  }
            } catch (e: JsonDataException) {
                _uiState.update { PictureListUiState(error = Error(isApiError = true))  }
            }
        }
    }
}

data class PictureListUiState(
    val isLoadingPictures: Boolean = false,
    val pictures: List<PictureListItemUiState> = listOf(),
    val error: Error? = null
)

data class PictureListItemUiState(
    val id: Int,
    val title: String,
    val date: String, // Formatted Date (string)
    val url: String
)

data class Error(
    val isNetworkError: Boolean = false,
    val isApiError: Boolean = false
)
