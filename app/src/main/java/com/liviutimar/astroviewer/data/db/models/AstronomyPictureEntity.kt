package com.liviutimar.astroviewer.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.liviutimar.astroviewer.domain.models.AstronomyPicture
import java.time.LocalDate

@Entity(tableName = "Pictures")
data class AstronomyPictureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Dummy value. Will be replaced by Room when inserting into the db
    val title: String,
    val desc: String,
    val date: LocalDate,
    val url: String,
)

fun AstronomyPictureEntity.asDomainModel() = AstronomyPicture(
    id = id,
    title = title,
    desc = desc,
    date = date,
    url = url
)

fun AstronomyPicture.asEntity() = AstronomyPictureEntity(
    title = title,
    desc = desc,
    date = date,
    url = url
)
