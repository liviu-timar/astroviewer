package com.liviutimar.astroviewer.ui.screens.picturedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liviutimar.astroviewer.domain.usecases.FormatDateUseCase
import com.liviutimar.astroviewer.domain.usecases.GetAstronomyPictureDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureDetailsViewModel @Inject constructor(
    private val getAstronomyPictureDetailsUseCase: GetAstronomyPictureDetailsUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PictureDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getPictureDetails(pictureId: Int) {
        viewModelScope.launch {
            getAstronomyPictureDetailsUseCase(pictureId).let {
                _uiState.value = PictureDetailsUiState(
                    details = PictureDetails(
                        title = it.title,
                        desc = it.desc,
                        date = formatDateUseCase(it.date),
                        url = it.url
                    )
                )
            }
        }
    }
}

data class PictureDetailsUiState(
    val details: PictureDetails? = null,
)

data class PictureDetails(
    val title: String = "",
    val desc: String = "",
    val date: String = "",
    val url: String = ""
)