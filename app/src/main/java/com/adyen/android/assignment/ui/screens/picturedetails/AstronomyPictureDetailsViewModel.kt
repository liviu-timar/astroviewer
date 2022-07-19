package com.adyen.android.assignment.ui.screens.picturedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.domain.usecases.FormatDateUseCase
import com.adyen.android.assignment.domain.usecases.GetAstronomyPictureDetailsUseCase
import com.adyen.android.assignment.ui.screens.picturedetails.models.PictureDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureDetailsViewModel @Inject constructor(
    private val getAstronomyPictureDetailsUseCase: GetAstronomyPictureDetailsUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModel() {

    private val _pictureDetails = MutableLiveData<PictureDetails>()
    val pictureDetails: LiveData<PictureDetails> = _pictureDetails

    fun getPictureDetails(pictureId: Int) {
        viewModelScope.launch {
            _pictureDetails.value = getAstronomyPictureDetailsUseCase(pictureId).let { picture ->
                PictureDetails(
                    title = picture.title,
                    desc = picture.desc,
                    date = formatDateUseCase(picture.date),
                    url = picture.url
                )
            }
        }
    }
}