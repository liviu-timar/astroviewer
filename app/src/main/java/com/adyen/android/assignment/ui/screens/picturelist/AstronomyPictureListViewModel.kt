package com.adyen.android.assignment.ui.screens.picturelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.domain.usecases.FormatDateUseCase
import com.adyen.android.assignment.domain.usecases.GetAstronomyPictureListUseCase
import com.adyen.android.assignment.domain.usecases.SortBy
import com.adyen.android.assignment.ui.screens.picturelist.models.PictureListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureListViewModel @Inject constructor(
    private val getAstronomyPictureListUseCase: GetAstronomyPictureListUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModel() {

    var isDataFirstLoad: Boolean = true
    var sortPicturesBy = SortBy.DATE_DESC

    private val _pictures = MutableStateFlow<List<PictureListItem>?>(null)
    val pictures = _pictures.asStateFlow()

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