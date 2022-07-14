package com.adyen.android.assignment.ui.picturelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.usecases.GetAstronomyPictureListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureListViewModel @Inject constructor(
    private val getAstronomyPictureListUseCase: GetAstronomyPictureListUseCase
) : ViewModel() {

    private val _pictures = MutableLiveData<List<AstronomyPicture>>()
    val pictures: LiveData<List<AstronomyPicture>> = _pictures

    fun getPictureList(count: Int) {
        viewModelScope.launch { _pictures.value = getAstronomyPictureListUseCase(count) }
    }
}