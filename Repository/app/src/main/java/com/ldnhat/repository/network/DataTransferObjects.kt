package com.ldnhat.repository.network

import com.ldnhat.repository.database.DatabaseVideo
import com.ldnhat.repository.domain.DevByteVideos
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos : List<NetworkVideo>)

@JsonClass(generateAdapter = true)
data class NetworkVideo(val title : String,
                        val description : String,
                        val url : String,
                        val updated : String,
                        val thumbnail : String,
                        val closedCaptions : String?
                        )

fun NetworkVideoContainer.asDomainModel() : List<DevByteVideos>{
    return videos.map {
        DevByteVideos(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}

fun NetworkVideoContainer.asDatabaseModel(): List<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}