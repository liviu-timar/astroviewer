package com.liviutimar.astroviewer.ui.screens.picturelist.models

data class PictureListItem(
    val id: Int,
    val title: String,
    val date: String, // Formatted Date (string)
    val url: String
)