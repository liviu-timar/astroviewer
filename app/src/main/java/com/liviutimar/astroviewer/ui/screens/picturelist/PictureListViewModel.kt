package com.liviutimar.astroviewer.ui.screens.picturelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liviutimar.astroviewer.domain.usecases.FormatDateUseCase
import com.liviutimar.astroviewer.domain.usecases.GetPictureListUseCase
import com.liviutimar.astroviewer.domain.usecases.SortBy
import com.squareup.moshi.JsonDataException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PictureListViewModel @Inject constructor(
    private val getPictureListUseCase: GetPictureListUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PictureListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getPictureList(refresh = true)
    }

    fun getPictureList(refresh: Boolean, count: Int = PICTURE_COUNT, sortBy: SortBy = SortBy.DATE_DESC) {
        viewModelScope.launch {
            if (refresh) _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val pictures = getPictureListUseCase(refresh, count, sortBy)

                _uiState.update {
                    it.copy(
                        pictures = pictures.map { picture ->
                            PictureListItemUiState(
                                id = picture.id,
                                title = picture.title,
                                date = formatDateUseCase(picture.date),
                                url = picture.url
                            )
                        },
                        isLoading = false,
                        sortBy = sortBy,
                        error = null
                    )
                }
            } catch (e: IOException) { // Domain or data layer should catch these exceptions and throw custom ones for the UI layer to handle
                _uiState.update {
                    it.copy(
                        error = Error(isNetworkError = true),
                        isLoading = false
                    )
                }
            } catch (e: JsonDataException) {
                _uiState.update {
                    it.copy(
                        error = Error(isApiError = true),
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class PictureListUiState(
    val isLoading: Boolean = false,
    val pictures: List<PictureListItemUiState> = emptyList(),
    val sortBy: SortBy = SortBy.DATE_DESC,
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

private const val PICTURE_COUNT = 15