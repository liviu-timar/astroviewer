package com.adyen.android.assignment.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adyen.android.assignment.domain.models.AstronomyPicture
import java.time.LocalDate

@Entity(tableName = "Pictures")
data class AstronomyPictureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val desc: String,
    val date: LocalDate,
    val url: String,
)

fun AstronomyPictureEntity.asDomainModel() = AstronomyPicture(
    title = title,
    desc = desc,
    date = date,
    url = url
)

fun AstronomyPicture.asEntity() = AstronomyPictureEntity(
    id = 0, // Dummy value. Will be replaced by Room when inserting into the db
    title = title,
    desc = desc,
    date = date,
    url = url
)
