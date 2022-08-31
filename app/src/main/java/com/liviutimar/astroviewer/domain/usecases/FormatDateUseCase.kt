package com.liviutimar.astroviewer.domain.usecases

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FormatDateUseCase @Inject constructor() {

    private val formatter = DateTimeFormatter.ofPattern("dd MMM yyy")

    operator fun invoke(date: LocalDate): String = date.format(formatter)
}