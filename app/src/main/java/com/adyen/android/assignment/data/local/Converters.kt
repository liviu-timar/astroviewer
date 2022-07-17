package com.adyen.android.assignment.data.local

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun localDateToString(date: LocalDate): String = date.toString()

    @TypeConverter
    fun stringToLocalDate(stringDate: String): LocalDate = LocalDate.parse(stringDate)
}