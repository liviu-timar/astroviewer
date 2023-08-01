package com.liviutimar.astroviewer.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.liviutimar.astroviewer.domain.models.Picture

@Entity(tableName = "Pictures")
data class PictureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Dummy value. Will be replaced by Room when inserting into the db
    val title: String,
    val desc: String,
    val date: String,
    val url: String,
    val isFavorite: Boolean,
)

fun PictureEntity.asDomainModel() = Picture(
    id = id,
    title = title,
    desc = desc,
    date = date,
    url = url,
    isFavorite = isFavorite
)

fun Picture.asEntity() = PictureEntity(
    title = title,
    desc = desc,
    date = date,
    url = url,
    isFavorite = isFavorite
)
