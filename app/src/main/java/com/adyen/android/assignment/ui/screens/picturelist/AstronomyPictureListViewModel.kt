package com.adyen.android.assignment.ui.screens.picturelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.domain.models.AstronomyPicture
import com.adyen.android.assignment.domain.usecases.GetSortedAstronomyPictureListUseCase
import com.adyen.android.assignment.domain.usecases.SortBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureListViewModel @Inject constructor(
    private val getSortedAstronomyPictureListUseCase: GetSortedAstronomyPictureListUseCase
) : ViewModel() {

    var isDataFirstLoad: Boolean = true

    private val _pictures = MutableLiveData<List<AstronomyPicture>>()
    val pictures: LiveData<List<AstronomyPicture>> = _pictures

    fun getPictureList(count: Int, sortBy: SortBy = SortBy.DATE_DESC) {
        viewModelScope.launch { _pictures.value = getSortedAstronomyPictureListUseCase(count, sortBy) }
    }
}