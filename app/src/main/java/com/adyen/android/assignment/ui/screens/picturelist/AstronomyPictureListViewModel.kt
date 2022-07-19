package com.adyen.android.assignment.ui.screens.picturelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.domain.usecases.FormatDateUseCase
import com.adyen.android.assignment.domain.usecases.GetAstronomyPictureListUseCase
import com.adyen.android.assignment.domain.usecases.SortBy
import com.adyen.android.assignment.ui.screens.picturelist.models.PictureListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureListViewModel @Inject constructor(
    private val getAstronomyPictureListUseCase: GetAstronomyPictureListUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModel() {

    var isDataFirstLoad: Boolean = true
    var sortPicturesBy = SortBy.DATE_DESC

    private val _pictures = MutableLiveData<List<PictureListItem>>()
    val pictures: LiveData<List<PictureListItem>> = _pictures

    fun getPictureList(refresh: Boolean = true, count: Int, sortBy: SortBy = SortBy.DATE_DESC) {
        viewModelScope.launch {
            _pictures.value = getAstronomyPictureListUseCase(refresh, count, sortBy)
                .map { picture ->
                    PictureListItem(
                        id = picture.id,
                        title = picture.title,
                        date = formatDateUseCase(picture.date),
                        url = picture.url
                    )
                }
        }
    }

    fun clearPictureList() {
        _pictures.value = null
    }
}