package com.liviutimar.astroviewer.ui.screens.picturedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liviutimar.astroviewer.domain.usecases.GetPictureDetailsUseCase
import com.liviutimar.astroviewer.domain.usecases.TogglePictureFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureDetailsViewModel @Inject constructor(
    private val getPictureDetailsUseCase: GetPictureDetailsUseCase,
    private val togglePictureFavoriteStatusUseCase: TogglePictureFavoriteStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PictureDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getPictureDetails(pictureId: Int) {
        viewModelScope.launch {
            getPictureDetailsUseCase(pictureId).let {
                _uiState.value = PictureDetailsUiState(
                    title = it.title,
                    desc = it.desc,
                    date = it.date,
                    url = it.url,
                    isFavorite = it.isFavorite
                )
            }
        }
    }

    fun toggleFavoriteStatus(pictureId: Int) = viewModelScope.launch {
        togglePictureFavoriteStatusUseCase(pictureId)
        getPictureDetails(pictureId) // Data request will be replaced by observing Room flow
    }
}

data class PictureDetailsUiState(
    val title: String = "",
    val desc: String = "",
    val date: String = "",
    val url: String = "",
    val isFavorite: Boolean = false,
)