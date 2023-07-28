package com.liviutimar.astroviewer.ui.screens.picturedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liviutimar.astroviewer.domain.usecases.FormatDateUseCase
import com.liviutimar.astroviewer.domain.usecases.GetPictureDetailsUseCase
import com.liviutimar.astroviewer.domain.usecases.TogglePictureFavoriteFlagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureDetailsViewModel @Inject constructor(
    private val getPictureDetailsUseCase: GetPictureDetailsUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val toggleFavoriteFlagUseCase: TogglePictureFavoriteFlagUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PictureDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getPictureDetails(pictureId: Int) {
        viewModelScope.launch {
            getPictureDetailsUseCase(pictureId).let {
                _uiState.value = PictureDetailsUiState(
                    details = PictureDetails(
                        title = it.title,
                        desc = it.desc,
                        date = formatDateUseCase(it.date),
                        url = it.url,
                        isFavorite = it.isFavorite,
                    )
                )
            }
        }
    }

    fun toggleFavoriteFlag(id: Int) = viewModelScope.launch {
        toggleFavoriteFlagUseCase(id)
        getPictureDetails(id) // Data request will be replaced by observing Room flow
    }
}

data class PictureDetailsUiState(
    val details: PictureDetails? = null,
)

data class PictureDetails(
    val title: String,
    val desc: String,
    val date: String, // Formatted Date (string)
    val url: String,
    val isFavorite: Boolean,
)