package com.ldnhat.repository.domain

import com.ldnhat.repository.util.smartTruncate

data class DevByteVideos(val title : String,
                         val description: String,
                         val url : String,
                         val updated: String,
                         val thumbnail:String
                         ){
    val shortDescription : String
    get() = description.smartTruncate(200)
}
