package com.adyen.android.assignment.ui.screens.picturelist.models

data class PictureListItem(
    val id: Int,
    val title: String,
    val date: String, // Formatted Date (string)
    val url: String
)