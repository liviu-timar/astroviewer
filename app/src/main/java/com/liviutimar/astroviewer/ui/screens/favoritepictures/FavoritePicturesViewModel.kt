package com.liviutimar.astroviewer.ui.screens.favoritepictures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liviutimar.astroviewer.domain.models.Picture
import com.liviutimar.astroviewer.domain.usecases.GetFavoritePicturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePicturesViewModel @Inject constructor(
    private val getFavoritePicturesUseCase: GetFavoritePicturesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritePicturesUiState())
    val uiState = _uiState.asStateFlow()

    fun getFavoritePictures() {
        viewModelScope.launch {
            _uiState.value = FavoritePicturesUiState(pictures = getFavoritePicturesUseCase())
        }
    }
}

data class FavoritePicturesUiState(
    val pictures: List<Picture> = emptyList()
)