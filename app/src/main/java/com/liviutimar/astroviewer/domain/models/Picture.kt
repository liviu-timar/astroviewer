package com.liviutimar.astroviewer.domain.models

import java.time.LocalDate

data class Picture(
    val id: Int = 0,
    val title: String,
    val desc: String,
    val date: LocalDate,
    val url: String,
    val isFavorite: Boolean = false,
)
