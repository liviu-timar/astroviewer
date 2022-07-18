package com.adyen.android.assignment.domain.models

import java.time.LocalDate

data class AstronomyPicture(
    val id: Int = 0,
    val title: String,
    val desc: String,
    val date: LocalDate,
    val url: String,
)
