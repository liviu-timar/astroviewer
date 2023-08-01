package com.liviutimar.astroviewer.domain.models

data class Picture(
    val id: Int = 0,
    val title: String,
    val desc: String,
    val date: String,
    val url: String,
    val isFavorite: Boolean = false,
)
