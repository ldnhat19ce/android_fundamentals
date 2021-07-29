package com.ldnhat.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ldnhat.repository.domain.DevByteVideos

@Entity
data class DatabaseVideo constructor(

    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

fun List<DatabaseVideo>.asDomainModel() : List<DevByteVideos>{
    return map {
        DevByteVideos(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}