package com.liviutimar.astroviewer.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.liviutimar.astroviewer.domain.models.Picture
import java.time.LocalDate

@Entity(tableName = "Pictures")
data class PictureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Dummy value. Will be replaced by Room when inserting into the db
    val title: String,
    val desc: String,
    val date: LocalDate,
    val url: String,
)

fun PictureEntity.asDomainModel() = Picture(
    id = id,
    title = title,
    desc = desc,
    date = date,
    url = url
)

fun Picture.asEntity() = PictureEntity(
    title = title,
    desc = desc,
    date = date,
    url = url
)
