package com.adyen.android.assignment.ui.screens.picturedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.usecases.GetAstronomyPictureDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureDetailsViewModel @Inject constructor(
    private val getAstronomyPictureDetailsUseCase: GetAstronomyPictureDetailsUseCase
) : ViewModel() {

    private val _pictureDetails = MutableLiveData<AstronomyPicture>()
    val pictureDetails: LiveData<AstronomyPicture> = _pictureDetails

    fun getPictureDetails(pictureId: Int) {
        viewModelScope.launch {
            _pictureDetails.value = getAstronomyPictureDetailsUseCase(pictureId)
        }
    }
}